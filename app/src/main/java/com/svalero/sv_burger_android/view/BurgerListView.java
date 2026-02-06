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
        // Asegúrate de que este layout tiene el RecyclerView y el Toolbar
        setContentView(R.layout.activity_main);

        // 1. Configurar Toolbar con flecha atrás
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Nuestras Burgers");
        }

        // 2. Inicializar Presenter y Lista
        presenter = new BurgerListPresenter(this);
        burgerList = new ArrayList<>();

        // 3. Configurar RecyclerView
        // IMPORTANTE: Verifica que en tu XML el ID sea 'rvFoodTrucks' o 'rvBurgers'
        RecyclerView recyclerView = findViewById(R.id.rvFoodTrucks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BurgerAdapter(burgerList);
        recyclerView.setAdapter(adapter);

        // NOTA: Hemos quitado presenter.loadBurgers() de aquí.
        // Lo ponemos en onResume para que refresque al volver.
    }

    // --- NUEVO: RECARGA AUTOMÁTICA ---
    @Override
    protected void onResume() {
        super.onResume();
        // Llamamos a la API cada vez que la pantalla se hace visible
        // Esto refrescará la lista si has borrado algo en la pantalla de detalle
        presenter.loadBurgers();
    }
    // ---------------------------------

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

    // Gestionar el click en la flecha de atrás de la barra superior
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Cierra esta actividad y vuelve a la anterior
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}