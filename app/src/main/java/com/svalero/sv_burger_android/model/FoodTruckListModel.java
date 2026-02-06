package com.svalero.sv_burger_android.model;

import android.util.Log;
import com.svalero.sv_burger_android.api.FoodTruckApi;
import com.svalero.sv_burger_android.api.FoodTruckApiInterface;
import com.svalero.sv_burger_android.contract.FoodTruckListContract;
import com.svalero.sv_burger_android.domain.FoodTruck;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodTruckListModel implements FoodTruckListContract.Model {

    @Override
    public void loadFoodTrucks(OnLoadFoodTrucksListener listener) {
        FoodTruckApiInterface api = FoodTruckApi.buildInstance();
        Call<List<FoodTruck>> call = api.getFoodTrucks();

        call.enqueue(new Callback<List<FoodTruck>>() {
            @Override
            public void onResponse(Call<List<FoodTruck>> call, Response<List<FoodTruck>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onLoadFoodTrucksSuccess(response.body());
                } else {
                    listener.onLoadFoodTrucksError("Error al cargar datos");
                }
            }

            @Override
            public void onFailure(Call<List<FoodTruck>> call, Throwable t) {
                Log.e("FoodTruckModel", "Error API", t);
                listener.onLoadFoodTrucksError("Error de conexión");
            }
        });
    }
}
