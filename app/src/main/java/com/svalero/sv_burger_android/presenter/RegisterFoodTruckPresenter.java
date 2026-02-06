package com.svalero.sv_burger_android.presenter;

import android.content.Context;

import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.contract.RegisterFoodTruckContract;
import com.svalero.sv_burger_android.domain.FoodTruck;
import com.svalero.sv_burger_android.model.RegisterFoodTruckModel;

public class RegisterFoodTruckPresenter implements RegisterFoodTruckContract.Presenter, RegisterFoodTruckContract.Model.OnRegisterListener {

    private RegisterFoodTruckContract.View view;
    private RegisterFoodTruckContract.Model model;
    private Context context;

    public RegisterFoodTruckPresenter(RegisterFoodTruckContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new RegisterFoodTruckModel();
    }

    @Override
    public void registerFoodTruck(FoodTruck foodTruck) {
        if (foodTruck.getNombre().isEmpty()) {
            view.showErrorMessage(context.getString(R.string.error_name_required));
            return;
        }
        model.registerFoodTruck(foodTruck, this);
    }

    @Override
    public void modifyFoodTruck(long id, FoodTruck foodTruck) {
        if (foodTruck.getNombre().isEmpty()) {
            view.showErrorMessage(context.getString(R.string.error_name_required));
            return;
        }
        model.modifyFoodTruck(id, foodTruck, this);
    }

    @Override
    public void onSuccess(FoodTruck foodTruck) {
        view.showSuccessMessage(context.getString(R.string.success_operation));
    }

    @Override
    public void onError(String message) {
        view.showErrorMessage(message);
    }
}