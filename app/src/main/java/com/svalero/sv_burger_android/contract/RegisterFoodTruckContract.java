package com.svalero.sv_burger_android.contract;

import com.svalero.sv_burger_android.domain.FoodTruck;

public interface RegisterFoodTruckContract {

    interface Model {
        interface OnRegisterListener {
            void onSuccess(FoodTruck foodTruck);
            void onError(String message);
        }
        void registerFoodTruck(FoodTruck foodTruck, OnRegisterListener listener);
        void modifyFoodTruck(long id, FoodTruck foodTruck, OnRegisterListener listener);
    }

    interface View {
        void showSuccessMessage(String message);
        void showErrorMessage(String message);
    }

    interface Presenter {
        void registerFoodTruck(FoodTruck foodTruck);
        void modifyFoodTruck(long id, FoodTruck foodTruck);
    }
}
