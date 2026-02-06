package com.svalero.sv_burger_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.contract.FoodTruckDetailContract;
import com.svalero.sv_burger_android.domain.FoodTruck;
import com.svalero.sv_burger_android.presenter.FoodTruckDetailPresenter;

public class FoodTruckDetailView extends AppCompatActivity implements FoodTruckDetailContract.View {

    private FoodTruckDetailPresenter presenter;
    private long foodTruckId;

    // Declaramos los TextViews aquí arriba para poder usarlos en todo el archivo
    private TextView tvName;
    private TextView tvDescription;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvRating;
    private boolean currentDeliveryOption = false;
    private FloatingActionButton fabAddBurger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_truck_detail);

        // 1. Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detalle FoodTruck");
        }

        presenter = new FoodTruckDetailPresenter(this);

        // 2. Recoger ID del Intent
        Intent intent = getIntent();
        foodTruckId = intent.getLongExtra("id", 0); // ¡ESTE DATO ES CLAVE!

        // 3. Inicializar las Vistas
        tvName = findViewById(R.id.tvDetailName);
        tvDescription = findViewById(R.id.tvDetailDescription);
        tvPhone = findViewById(R.id.tvDetailPhone);
        tvEmail = findViewById(R.id.tvDetailEmail);
        tvRating = findViewById(R.id.tvDetailRating);

        // 4. Pintar datos iniciales
        tvName.setText(intent.getStringExtra("name"));
        tvDescription.setText(intent.getStringExtra("description"));
        tvPhone.setText("📞 " + intent.getStringExtra("phone"));
        tvEmail.setText("📧 " + intent.getStringExtra("email"));
        tvRating.setText("⭐ " + intent.getFloatExtra("rating", 0));

        // 5. Botón Borrar
        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> showDeleteConfirmation());

        // 6. Botón Editar
        Button btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(this, RegisterFoodTruckView.class);
            editIntent.putExtra("id", foodTruckId);
            editIntent.putExtra("name", tvName.getText().toString());
            editIntent.putExtra("description", tvDescription.getText().toString());

            String phoneClean = tvPhone.getText().toString().replace("📞 ", "");
            String emailClean = tvEmail.getText().toString().replace("📧 ", "");
            String ratingClean = tvRating.getText().toString().replace("⭐ ", "");

            editIntent.putExtra("phone", phoneClean);
            editIntent.putExtra("email", emailClean);

            try {
                editIntent.putExtra("rating", Float.parseFloat(ratingClean));
            } catch (NumberFormatException | NullPointerException e) {
                editIntent.putExtra("rating", 0f);
            }

            editIntent.putExtra("opcionEnvios", currentDeliveryOption);
            startActivity(editIntent);
        });

        fabAddBurger = findViewById(R.id.fabAddBurger);
        fabAddBurger.setOnClickListener(v -> {
            // Saltamos a la pantalla de crear Burger
            Intent addBurgerIntent = new Intent(FoodTruckDetailView.this, RegisterBurgerView.class);
            // ¡IMPORTANTE! Nos llevamos el ID del Food Truck para saber "quién es el padre"
            addBurgerIntent.putExtra("food_truck_id", foodTruckId);
            startActivity(addBurgerIntent);
        });
    }

    // --- MÉTODOS DEL CICLO DE VIDA ---

    @Override
    protected void onResume() {
        super.onResume();
        // ESTA ES LA CLAVE: Al volver de editar, pedimos los datos nuevos
        presenter.loadFoodTruck(foodTruckId);
    }

    // --- MÉTODOS DEL CONTRATO (View) ---

    @Override
    public void showFoodTruck(FoodTruck foodTruck) {
        // Aquí actualizamos la pantalla con lo que viene fresco de la API
        tvName.setText(foodTruck.getNombre());
        tvDescription.setText(foodTruck.getDescripcion());
        tvPhone.setText("📞 " + foodTruck.getTelefono());
        tvEmail.setText("📧 " + foodTruck.getEmail());

        if (foodTruck.getValoracion() != null) {
            tvRating.setText("⭐ " + foodTruck.getValoracion());
        } else {
            tvRating.setText("⭐ 0.0");
        }
        if (foodTruck.getOpcionEnvios() != null) {
            this.currentDeliveryOption = foodTruck.getOpcionEnvios();
        }

    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish(); // Cerramos la pantalla tras borrar
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // --- OTROS MÉTODOS ---

    private void showDeleteConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Borrar FoodTruck")
                .setMessage("¿Estás seguro? Esta acción no se puede deshacer.")
                .setPositiveButton("Sí, borrar", (dialog, which) -> presenter.deleteFoodTruck(foodTruckId))
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}