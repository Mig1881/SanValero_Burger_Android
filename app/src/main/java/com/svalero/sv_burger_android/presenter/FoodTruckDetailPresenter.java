package com.svalero.sv_burger_android.presenter;

import com.svalero.sv_burger_android.contract.FoodTruckDetailContract;
import com.svalero.sv_burger_android.domain.FoodTruck;
import com.svalero.sv_burger_android.model.FoodTruckDetailModel;

public class FoodTruckDetailPresenter implements FoodTruckDetailContract.Presenter, FoodTruckDetailContract.Model.OnDetailListener {

    private FoodTruckDetailContract.View view;
    private FoodTruckDetailContract.Model model;

    public FoodTruckDetailPresenter(FoodTruckDetailContract.View view) {
        this.view = view;
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
        view.showSuccessMessage("FoodTruck eliminado");
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