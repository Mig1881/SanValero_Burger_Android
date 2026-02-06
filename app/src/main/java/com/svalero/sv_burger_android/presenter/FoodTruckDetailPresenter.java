package com.svalero.sv_burger_android.presenter;

import android.content.Context;

import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.contract.FoodTruckDetailContract;
import com.svalero.sv_burger_android.domain.FoodTruck;
import com.svalero.sv_burger_android.model.FoodTruckDetailModel;

public class FoodTruckDetailPresenter implements FoodTruckDetailContract.Presenter, FoodTruckDetailContract.Model.OnDetailListener {

    private FoodTruckDetailContract.View view;
    private FoodTruckDetailContract.Model model;
    private Context context;

    // Actualizamos el constructor
    public FoodTruckDetailPresenter(FoodTruckDetailContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new FoodTruckDetailModel();
    }

    @Override
    public void deleteFoodTruck(long id) {
        model.deleteFoodTruck(id, this);
    }

    @Override
    public void loadFoodTruck(long id) {
        model.loadFoodTruck(id, this);
    }

    @Override
    public void onDeleteSuccess() {
        // --- CAMBIO: Mensaje traducido desde strings.xml ---
        view.showSuccessMessage(context.getString(R.string.success_delete_truck));
    }

    @Override
    public void onDeleteError(String message) {
        view.showErrorMessage(message);
    }

    @Override
    public void onLoadSuccess(FoodTruck foodTruck) {
        view.showFoodTruck(foodTruck);
    }

    @Override
    public void onLoadError(String message) {
        view.showErrorMessage(message);
    }
}