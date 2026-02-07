package com.svalero.sv_burger_android.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox; // Importar CheckBox
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
    private CheckBox cbFilterVegan; // Referencia al checkbox

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // IMPORTANTE: Cambia al nuevo layout que creamos en el paso 1
        setContentView(R.layout.activity_burger_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.menu_burgers);
        }

        presenter = new BurgerListPresenter(this);
        burgerList = new ArrayList<>();

        // Inicializamos el CheckBox
        cbFilterVegan = findViewById(R.id.cbFilterVegan);

        // OJO: Cambia el ID del RecyclerView al que pusimos en activity_burger_list.xml (rvBurgers)
        // Antes usabas rvFoodTrucks, que es confuso para una lista de burgers
        RecyclerView recyclerView = findViewById(R.id.rvBurgers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BurgerAdapter(burgerList);
        recyclerView.setAdapter(adapter);

        // --- LISTENER DEL CHECKBOX ---
        cbFilterVegan.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Si está marcado (true) -> enviamos true
            // Si NO está marcado (false) -> enviamos null (para que la API devuelva todo)
            Boolean filter = isChecked ? true : null;
            presenter.loadBurgers(filter);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cargamos según el estado actual del checkbox
        Boolean filter = cbFilterVegan.isChecked() ? true : null;
        presenter.loadBurgers(filter);
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