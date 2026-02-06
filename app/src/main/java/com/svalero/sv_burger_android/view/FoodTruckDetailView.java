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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title_food_truck_detail);
        }

        presenter = new FoodTruckDetailPresenter(this, this);

        Intent intent = getIntent();
        foodTruckId = intent.getLongExtra("id", 0);

        tvName = findViewById(R.id.tvDetailName);
        tvDescription = findViewById(R.id.tvDetailDescription);
        tvPhone = findViewById(R.id.tvDetailPhone);
        tvEmail = findViewById(R.id.tvDetailEmail);
        tvRating = findViewById(R.id.tvDetailRating);

        tvName.setText(intent.getStringExtra("name"));
        tvDescription.setText(intent.getStringExtra("description"));
        tvPhone.setText(getString(R.string.fmt_phone_icon, intent.getStringExtra("phone")));
        tvEmail.setText(getString(R.string.fmt_email_icon, intent.getStringExtra("email")));
        tvRating.setText(getString(R.string.fmt_rating_icon, intent.getFloatExtra("rating", 0)));

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> showDeleteConfirmation());

        Button btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(this, RegisterFoodTruckView.class);
            editIntent.putExtra("id", foodTruckId);
            editIntent.putExtra("name", tvName.getText().toString());
            editIntent.putExtra("description", tvDescription.getText().toString());

            String phoneIcon = getString(R.string.phone_icon);
            String emailIcon = getString(R.string.email_icon);
            String ratingIcon = getString(R.string.rating_icon);

            editIntent.putExtra("phone", tvPhone.getText().toString().replace(phoneIcon + " ", ""));
            editIntent.putExtra("email", tvEmail.getText().toString().replace(emailIcon + " ", ""));

            String ratingClean = tvRating.getText().toString().replace(ratingIcon + " ", "");
            try {
                editIntent.putExtra("rating", Float.parseFloat(ratingClean.replace(",", ".")));
            } catch (Exception e) {
                editIntent.putExtra("rating", 0f);
            }

            editIntent.putExtra("opcionEnvios", currentDeliveryOption);
            startActivity(editIntent);
        });

        fabAddBurger = findViewById(R.id.fabAddBurger);
        fabAddBurger.setOnClickListener(v -> {
            Intent addBurgerIntent = new Intent(this, RegisterBurgerView.class);
            addBurgerIntent.putExtra("food_truck_id", foodTruckId);
            startActivity(addBurgerIntent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadFoodTruck(foodTruckId);
    }

    @Override
    public void showFoodTruck(FoodTruck foodTruck) {
        tvName.setText(foodTruck.getNombre());
        tvDescription.setText(foodTruck.getDescripcion());
        tvPhone.setText(getString(R.string.fmt_phone_icon, foodTruck.getTelefono()));
        tvEmail.setText(getString(R.string.fmt_email_icon, foodTruck.getEmail()));

        float ratingValue = (foodTruck.getValoracion() != null) ? foodTruck.getValoracion() : 0.0f;
        tvRating.setText(getString(R.string.fmt_rating_icon, ratingValue));

        if (foodTruck.getOpcionEnvios() != null) {
            this.currentDeliveryOption = foodTruck.getOpcionEnvios();
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

    private void showDeleteConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_delete_truck_title)
                .setMessage(R.string.dialog_delete_truck_msg)
                .setPositiveButton(R.string.btn_confirm_delete, (dialog, which) -> presenter.deleteFoodTruck(foodTruckId))
                .setNegativeButton(R.string.btn_cancel, null)
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