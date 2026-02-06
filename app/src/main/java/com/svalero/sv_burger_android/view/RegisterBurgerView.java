package com.svalero.sv_burger_android.view;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.contract.RegisterBurgerContract;
import com.svalero.sv_burger_android.presenter.RegisterBurgerPresenter;


public class RegisterBurgerView extends AppCompatActivity implements RegisterBurgerContract.View {

    private ImageView ivBurgerPreview;
    private EditText etName;
    private EditText etIngredients;
    private EditText etPrice;
    private CheckBox cbVegan;
    private Button btnSave;

    private long foodTruckId;
    private Uri selectedImageUri;

    private RegisterBurgerContract.Presenter presenter;

    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    ivBurgerPreview.setImageTintList(null);
                    ivBurgerPreview.setColorFilter(null);
                    ivBurgerPreview.setPadding(0, 0, 0, 0);
                    ivBurgerPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(this)
                            .load(uri)
                            .centerCrop()
                            .into(ivBurgerPreview);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_burger);

        presenter = new RegisterBurgerPresenter(this);

        // Recoger ID del FoodTruck
        foodTruckId = getIntent().getLongExtra("food_truck_id", -1);
        if (foodTruckId == -1) {
            Toast.makeText(this, "Error: No se ha detectado el FoodTruck", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        ivBurgerPreview = findViewById(R.id.ivBurgerPreview);
        etName = findViewById(R.id.etBurgerName);
        etIngredients = findViewById(R.id.etBurgerIngredients);
        etPrice = findViewById(R.id.etBurgerPrice);
        cbVegan = findViewById(R.id.cbVegan);
        btnSave = findViewById(R.id.btnSaveBurger);

        btnSave.setOnClickListener(v -> {
            presenter.addBurger(
                    etName.getText().toString(),
                    etIngredients.getText().toString(),
                    etPrice.getText().toString(),
                    cbVegan.isChecked(),
                    foodTruckId,
                    selectedImageUri,
                    this
            );
        });

        ivBurgerPreview.setOnClickListener(v -> {
            pickImageLauncher.launch("image/*");
        });
    }


    @Override
    public void showSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}