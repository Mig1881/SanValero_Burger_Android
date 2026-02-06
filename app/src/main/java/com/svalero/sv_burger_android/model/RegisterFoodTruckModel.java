package com.svalero.sv_burger_android.model;

import com.svalero.sv_burger_android.api.FoodTruckApi;
import com.svalero.sv_burger_android.api.FoodTruckApiInterface;
import com.svalero.sv_burger_android.contract.RegisterFoodTruckContract;
import com.svalero.sv_burger_android.domain.FoodTruck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFoodTruckModel implements RegisterFoodTruckContract.Model {

    @Override
    public void registerFoodTruck(FoodTruck foodTruck, OnRegisterListener listener) {
        FoodTruckApiInterface api = FoodTruckApi.buildInstance();
        Call<FoodTruck> call = api.addFoodTruck(foodTruck);
        call.enqueue(new Callback<FoodTruck>() {
            @Override
            public void onResponse(Call<FoodTruck> call, Response<FoodTruck> response) {
                if (response.isSuccessful()) listener.onSuccess(response.body());
                else listener.onError("Error al crear: " + response.code());
            }
            @Override
            public void onFailure(Call<FoodTruck> call, Throwable t) {
                listener.onError("Error de conexión");
            }
        });
    }

    @Override
    public void modifyFoodTruck(long id, FoodTruck foodTruck, OnRegisterListener listener) {
        FoodTruckApiInterface api = FoodTruckApi.buildInstance();
        Call<FoodTruck> call = api.modifyFoodTruck(id, foodTruck);
        call.enqueue(new Callback<FoodTruck>() {
            @Override
            public void onResponse(Call<FoodTruck> call, Response<FoodTruck> response) {
                if (response.isSuccessful()) listener.onSuccess(response.body());
                else listener.onError("Error al modificar: " + response.code());
            }
            @Override
            public void onFailure(Call<FoodTruck> call, Throwable t) {
                listener.onError("Error de conexión");
            }
        });
    }
}
