package com.svalero.sv_burger_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.contract.BurgerDetailContract;
import com.svalero.sv_burger_android.domain.Burger;
import com.svalero.sv_burger_android.presenter.BurgerDetailPresenter;

public class BurgerDetailView extends AppCompatActivity implements BurgerDetailContract.View {

    private ImageView ivImage;
    private TextView tvName, tvPrice, tvIngredients, tvVegan, tvDate;
    private Button btnEdit, btnDelete;
    private BurgerDetailPresenter presenter;
    private long burgerId;

    private Burger currentBurger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger_detail);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        burgerId = getIntent().getLongExtra("burger_id", -1);
        if (burgerId == -1) {
            finish();
            return;
        }

        initViews();
        presenter = new BurgerDetailPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null && burgerId != -1) {
            presenter.loadBurger(burgerId);
        }
    }

    private void initViews() {
        ivImage = findViewById(R.id.ivDetailImage);
        tvName = findViewById(R.id.tvDetailName);
        tvPrice = findViewById(R.id.tvDetailPrice);
        tvIngredients = findViewById(R.id.tvDetailIngredients);
        tvVegan = findViewById(R.id.tvDetailVegan);
        tvDate = findViewById(R.id.tvDetailDate);
        btnEdit = findViewById(R.id.btnDetailEdit);
        btnDelete = findViewById(R.id.btnDetailDelete);

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.dialog_delete_title)
                    .setMessage(R.string.dialog_delete_msg)
                    .setPositiveButton(R.string.btn_confirm_delete, (dialog, which) -> presenter.deleteBurger(burgerId))
                    .setNegativeButton(R.string.btn_cancel, null)
                    .show();
        });

        btnEdit.setOnClickListener(v -> {
            if (currentBurger == null) return;

            Intent intent = new Intent(this, RegisterBurgerView.class);
            intent.putExtra("edit_burger_id", currentBurger.getId());
            intent.putExtra("edit_name", currentBurger.getNombre());
            intent.putExtra("edit_ingredients", currentBurger.getIngredientes());
            intent.putExtra("edit_price", currentBurger.getPrecio());
            intent.putExtra("edit_vegan", currentBurger.isOpcionVegana());
            intent.putExtra("edit_image_url", currentBurger.getImagenURL());

            startActivity(intent);
        });
    }

    @Override
    public void showBurgerDetail(Burger burger) {
        this.currentBurger = burger;

        tvName.setText(burger.getNombre());

        tvPrice.setText(getString(R.string.fmt_price, burger.getPrecio()));

        tvIngredients.setText(burger.getIngredientes());

        tvDate.setText(getString(R.string.fmt_date, burger.getFechaCreacion()));

        if (burger.isOpcionVegana()) {
            tvVegan.setVisibility(View.VISIBLE);
        } else {
            tvVegan.setVisibility(View.GONE);
        }

        if (burger.getImagenURL() != null && !burger.getImagenURL().isEmpty()) {
            String fullUrl = "http://10.0.2.2:8080" + burger.getImagenURL();
            ivImage.setColorFilter(null);

            Glide.with(this)
                    .load(fullUrl)
                    .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .centerCrop()
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .into(ivImage);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
}