package com.svalero.sv_burger_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull; // Importante
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Importante
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.adapter.FoodTruckAdapter;
import com.svalero.sv_burger_android.contract.FoodTruckListContract;
import com.svalero.sv_burger_android.domain.FoodTruck;
import com.svalero.sv_burger_android.presenter.FoodTruckListPresenter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodTruckListContract.View {

    private FoodTruckListPresenter presenter;
    private FoodTruckAdapter adapter;
    private List<FoodTruck> foodTruckList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. CONFIGURAR TOOLBAR (Esto es lo que la hace funcionar como Action Bar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new FoodTruckListPresenter(this);
        foodTruckList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rvFoodTrucks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FoodTruckAdapter(foodTruckList);
        recyclerView.setAdapter(adapter);

        presenter.loadFoodTrucks();
    }

    // 2. CREAR EL MENÚ (Inflar el XML que hicimos)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // 3. GESTIONAR CLICS EN EL MENÚ
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_foodtruck) {
            Toast.makeText(this, "Próximamente: Añadir FoodTruck", Toast.LENGTH_SHORT).show();
            // Aquí pondremos el Intent para ir al registro más adelante
            return true;
        } else if (item.getItemId() == R.id.action_burgers) {
            Toast.makeText(this, "Próximamente: Lista de Hamburguesas", Toast.LENGTH_SHORT).show();
            // Aquí pondremos el Intent para ir a las burgers
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ... (El resto de métodos showFoodTrucks, showErrorMessage siguen igual) ...
    @Override
    public void showFoodTrucks(List<FoodTruck> foodTrucks) {
        foodTruckList.clear();
        foodTruckList.addAll(foodTrucks);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}