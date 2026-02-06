package com.svalero.sv_burger_android.contract;

import com.svalero.sv_burger_android.domain.FoodTruck;
import java.util.List;

public interface FoodTruckListContract {

    interface Model {
        interface OnLoadFoodTrucksListener {
            void onLoadFoodTrucksSuccess(List<FoodTruck> foodTrucks);
            void onLoadFoodTrucksError(String message);
        }
        void loadFoodTrucks(OnLoadFoodTrucksListener listener);
    }

    interface View {
        void showFoodTrucks(List<FoodTruck> foodTrucks);
        void showErrorMessage(String message);
    }

    interface Presenter {
        void loadFoodTrucks();
    }
}
