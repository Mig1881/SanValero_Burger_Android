package com.svalero.sv_burger_android.presenter;

import com.svalero.sv_burger_android.contract.FoodTruckListContract;
import com.svalero.sv_burger_android.domain.FoodTruck;
import com.svalero.sv_burger_android.model.FoodTruckListModel;

import java.util.List;

public class FoodTruckListPresenter implements FoodTruckListContract.Presenter, FoodTruckListContract.Model.OnLoadFoodTrucksListener {

    private FoodTruckListContract.View view;
    private FoodTruckListContract.Model model;

    public FoodTruckListPresenter(FoodTruckListContract.View view) {
        this.view = view;
        this.model = new FoodTruckListModel();
    }

    @Override
    public void loadFoodTrucks() {
        model.loadFoodTrucks(this);
    }

    @Override
    public void onLoadFoodTrucksSuccess(List<FoodTruck> foodTrucks) {
        view.showFoodTrucks(foodTrucks);
    }

    @Override
    public void onLoadFoodTrucksError(String message) {
        view.showErrorMessage(message);
    }
}
