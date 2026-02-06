package com.svalero.sv_burger_android.model;

import com.svalero.sv_burger_android.api.FoodTruckApi;
import com.svalero.sv_burger_android.contract.FoodTruckDetailContract;
import com.svalero.sv_burger_android.domain.FoodTruck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodTruckDetailModel implements FoodTruckDetailContract.Model {

    @Override
    public void deleteFoodTruck(long id, OnDetailListener listener) {
        FoodTruckApi.buildInstance().deleteFoodTruck(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) listener.onDeleteSuccess();
                else listener.onDeleteError("Error borrando");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteError("Error conexión");
            }
        });
    }

    @Override
    public void loadFoodTruck(long id, OnDetailListener listener) {
        FoodTruckApi.buildInstance().getFoodTruck(id).enqueue(new Callback<FoodTruck>() {
            @Override
            public void onResponse(Call<FoodTruck> call, Response<FoodTruck> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onLoadSuccess(response.body());
                } else {
                    listener.onLoadError("Error cargando detalle");
                }
            }
            @Override
            public void onFailure(Call<FoodTruck> call, Throwable t) {
                listener.onLoadError("Error de conexión");
            }
        });
    }
}