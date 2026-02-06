package com.svalero.sv_burger_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        presenter = new FoodTruckListPresenter(this);
        foodTruckList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rvFoodTrucks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FoodTruckAdapter(foodTruckList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadFoodTrucks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_foodtruck) {
            Intent intent = new Intent(this, RegisterFoodTruckView.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_burgers) {
            Intent intent = new Intent(this, BurgerListView.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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