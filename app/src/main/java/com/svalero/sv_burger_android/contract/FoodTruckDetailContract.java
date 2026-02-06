package com.svalero.sv_burger_android.contract;

import com.svalero.sv_burger_android.domain.FoodTruck;

public interface FoodTruckDetailContract {

    interface Model {
        interface OnDetailListener {
            void onDeleteSuccess();
            void onDeleteError(String message);
            void onLoadSuccess(FoodTruck foodTruck);
            void onLoadError(String message);
        }
        void deleteFoodTruck(long id, OnDetailListener listener);
        void loadFoodTruck(long id, OnDetailListener listener);
    }

    interface View {
        void showSuccessMessage(String message);
        void showErrorMessage(String message);
        void showFoodTruck(FoodTruck foodTruck);
    }

    interface Presenter {
        void deleteFoodTruck(long id);
        void loadFoodTruck(long id);
    }
}
