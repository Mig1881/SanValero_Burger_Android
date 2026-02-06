package com.svalero.sv_burger_android.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.adapter.BurgerAdapter;
import com.svalero.sv_burger_android.contract.BurgerListContract;
import com.svalero.sv_burger_android.domain.Burger;
import com.svalero.sv_burger_android.presenter.BurgerListPresenter;

import java.util.ArrayList;
import java.util.List;

public class BurgerListView extends AppCompatActivity implements BurgerListContract.View {

    private BurgerListPresenter presenter;
    private BurgerAdapter adapter;
    private List<Burger> burgerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Configurar Toolbar con flecha atrás
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // USAMOS EL RECURSO DE CADENA PARA EL TÍTULO
            getSupportActionBar().setTitle(R.string.menu_burgers);
        }

        // 2. Inicializar Presenter y Lista
        presenter = new BurgerListPresenter(this);
        burgerList = new ArrayList<>();

        // 3. Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvFoodTrucks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BurgerAdapter(burgerList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadBurgers();
    }

    @Override
    public void showBurgers(List<Burger> burgers) {
        burgerList.clear();
        burgerList.addAll(burgers);
        adapter.notifyDataSetChanged();
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