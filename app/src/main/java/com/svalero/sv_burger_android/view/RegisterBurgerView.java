package com.svalero.sv_burger_android.view;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
    private EditText etName, etIngredients, etPrice;
    private CheckBox cbVegan;
    private Button btnSave;
    private TextView tvTitle;

    private RegisterBurgerContract.Presenter presenter;
    private Uri selectedImageUri;

    private boolean isEditMode = false;
    private long burgerIdToEdit = -1;
    private long foodTruckId = -1;

    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    ivBurgerPreview.setImageTintList(null);
                    ivBurgerPreview.setColorFilter(null);
                    ivBurgerPreview.setPadding(0, 0, 0, 0);
                    ivBurgerPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(this).load(uri).centerCrop().into(ivBurgerPreview);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_burger);

        presenter = new RegisterBurgerPresenter(this);
        initViews();

        burgerIdToEdit = getIntent().getLongExtra("edit_burger_id", -1);

        if (burgerIdToEdit != -1) {
            isEditMode = true;
            setupEditMode();
        } else {
            isEditMode = false;
            foodTruckId = getIntent().getLongExtra("food_truck_id", -1);
            if (foodTruckId == -1) {
                // USAMOS RECURSO PARA EL ERROR DE ID
                Toast.makeText(this, R.string.error_missing_id, Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        btnSave.setOnClickListener(v -> {
            if (isEditMode) {
                presenter.editBurger(
                        burgerIdToEdit,
                        etName.getText().toString(),
                        etIngredients.getText().toString(),
                        etPrice.getText().toString(),
                        cbVegan.isChecked(),
                        selectedImageUri,
                        this
                );
            } else {
                presenter.addBurger(
                        etName.getText().toString(),
                        etIngredients.getText().toString(),
                        etPrice.getText().toString(),
                        cbVegan.isChecked(),
                        foodTruckId,
                        selectedImageUri,
                        this
                );
            }
        });

        ivBurgerPreview.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
    }

    private void initViews() {
        ivBurgerPreview = findViewById(R.id.ivBurgerPreview);
        etName = findViewById(R.id.etBurgerName);
        etIngredients = findViewById(R.id.etBurgerIngredients);
        etPrice = findViewById(R.id.etBurgerPrice);
        cbVegan = findViewById(R.id.cbVegan);
        btnSave = findViewById(R.id.btnSaveBurger);
        tvTitle = findViewById(R.id.tvScreenTitle);
    }

    private void setupEditMode() {
        // CAMBIAMOS TEXTOS VISUALES USANDO STRINGS
        btnSave.setText(R.string.btn_update_burger);
        if (tvTitle != null) {
            tvTitle.setText(R.string.title_edit_burger);
        }

        String name = getIntent().getStringExtra("edit_name");
        String ingredients = getIntent().getStringExtra("edit_ingredients");
        float price = getIntent().getFloatExtra("edit_price", 0);
        boolean vegan = getIntent().getBooleanExtra("edit_vegan", false);
        String currentImageUrl = getIntent().getStringExtra("edit_image_url");

        etName.setText(name);
        etIngredients.setText(ingredients);
        etPrice.setText(String.valueOf(price));
        cbVegan.setChecked(vegan);

        if (currentImageUrl != null && !currentImageUrl.isEmpty()) {
            String fullUrl = "http://10.0.2.2:8080" + currentImageUrl;

            ivBurgerPreview.setImageTintList(null);
            ivBurgerPreview.setColorFilter(null);
            ivBurgerPreview.setPadding(0, 0, 0, 0);
            ivBurgerPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);

            Glide.with(this)
                    .load(fullUrl)
                    .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivBurgerPreview);
        }
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