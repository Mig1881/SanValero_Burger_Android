package com.svalero.sv_burger_android.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.contract.RegisterFoodTruckContract;
import com.svalero.sv_burger_android.domain.FoodTruck;
import com.svalero.sv_burger_android.presenter.RegisterFoodTruckPresenter;

public class RegisterFoodTruckView extends AppCompatActivity implements RegisterFoodTruckContract.View {

    private RegisterFoodTruckPresenter presenter;
    private EditText etName, etDesc, etPhone, etEmail, etRating;
    private CheckBox cbDelivery;
    private Button btnSave;

    private boolean isEditMode = false;
    private long foodTruckId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_food_truck);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Nuevo FoodTruck");
        }

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDescription);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etRating = findViewById(R.id.etRating);
        cbDelivery = findViewById(R.id.cbDelivery);
        btnSave = findViewById(R.id.btnSave);

        presenter = new RegisterFoodTruckPresenter(this);

        if (getIntent().hasExtra("id")) {
            isEditMode = true;
            foodTruckId = getIntent().getLongExtra("id", 0);

            if (getSupportActionBar() != null) getSupportActionBar().setTitle("Editar FoodTruck");
            btnSave.setText("GUARDAR CAMBIOS");

            etName.setText(getIntent().getStringExtra("name"));
            etDesc.setText(getIntent().getStringExtra("description"));
            etPhone.setText(getIntent().getStringExtra("phone"));
            etEmail.setText(getIntent().getStringExtra("email"));
            etRating.setText(String.valueOf(getIntent().getFloatExtra("rating", 0)));
            boolean envio = getIntent().getBooleanExtra("opcionEnvios", false);
            cbDelivery.setChecked(envio);

        }

        btnSave.setOnClickListener(v -> saveAction());
    }

    private void saveAction() {
        String name = etName.getText().toString();
        String desc = etDesc.getText().toString();
        String phone = etPhone.getText().toString();
        String email = etEmail.getText().toString();
        String ratingText = etRating.getText().toString();

        if (name.isEmpty()) {
            showErrorMessage("El nombre es obligatorio.");
            return;
        }

        if (desc.length() < 10) {
            showErrorMessage("La descripción debe tener al menos 10 caracteres.");
            return;
        }

        float rating = 0;
        try {
            String cleanRating = ratingText.replace(",", ".").trim();
            if (!cleanRating.isEmpty()) {
                rating = Float.parseFloat(cleanRating);
            }
        } catch (NumberFormatException e) {
            rating = 0;
        }

        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setNombre(name);
        foodTruck.setDescripcion(desc);
        foodTruck.setTelefono(phone);
        foodTruck.setEmail(email);
        foodTruck.setValoracion(rating);
        foodTruck.setOpcionEnvios(cbDelivery.isChecked());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            foodTruck.setFechaInscripcion(java.time.LocalDate.now().toString());
        } else {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
            foodTruck.setFechaInscripcion(sdf.format(new java.util.Date()));
        }
        android.util.Log.d("FoodTruckApp", "Enviando Valoracion: " + rating);


        if (isEditMode) {
            foodTruck.setId(foodTruckId);
            presenter.modifyFoodTruck(foodTruckId, foodTruck);
        } else {
            presenter.registerFoodTruck(foodTruck);
        }
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
