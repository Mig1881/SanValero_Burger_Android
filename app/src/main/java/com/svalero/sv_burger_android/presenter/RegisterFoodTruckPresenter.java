package com.svalero.sv_burger_android.presenter;

import com.svalero.sv_burger_android.contract.RegisterFoodTruckContract;
import com.svalero.sv_burger_android.domain.FoodTruck;
import com.svalero.sv_burger_android.model.RegisterFoodTruckModel;

public class RegisterFoodTruckPresenter implements RegisterFoodTruckContract.Presenter, RegisterFoodTruckContract.Model.OnRegisterListener {

    private RegisterFoodTruckContract.View view;
    private RegisterFoodTruckContract.Model model;

    public RegisterFoodTruckPresenter(RegisterFoodTruckContract.View view) {
        this.view = view;
        this.model = new RegisterFoodTruckModel();
    }

    @Override
    public void registerFoodTruck(FoodTruck foodTruck) {
        if (foodTruck.getNombre().isEmpty()) {
            view.showErrorMessage("El nombre es obligatorio");
            return;
        }
        model.registerFoodTruck(foodTruck, this);
    }

    @Override
    public void modifyFoodTruck(long id, FoodTruck foodTruck) {
        if (foodTruck.getNombre().isEmpty()) {
            view.showErrorMessage("El nombre es obligatorio");
            return;
        }
        model.modifyFoodTruck(id, foodTruck, this);
    }

    @Override
    public void onSuccess(FoodTruck foodTruck) {
        view.showSuccessMessage("Operación realizada con éxito");
    }

    @Override
    public void onError(String message) {
        view.showErrorMessage(message);
    }
}
