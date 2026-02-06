package com.svalero.sv_burger_android.model;

import com.svalero.sv_burger_android.api.BurgerApi;
import com.svalero.sv_burger_android.api.BurgerApiInterface;
import com.svalero.sv_burger_android.contract.BurgerDetailContract;
import com.svalero.sv_burger_android.domain.Burger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BurgerDetailModel implements BurgerDetailContract.Model {

    private BurgerApiInterface api;

    public BurgerDetailModel() {
        this.api = BurgerApi.buildInstance();
    }

    @Override
    public void loadBurger(long id, OnLoadBurgerListener listener) {
        Call<Burger> call = api.getBurgerDetail(id);
        call.enqueue(new Callback<Burger>() {
            @Override
            public void onResponse(Call<Burger> call, Response<Burger> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onLoadSuccess(response.body());
                } else {
                    listener.onLoadError("Error al cargar datos");
                }
            }

            @Override
            public void onFailure(Call<Burger> call, Throwable t) {
                listener.onLoadError("Fallo de red: " + t.getMessage());
            }
        });
    }

    @Override
    public void deleteBurger(long id, OnDeleteBurgerListener listener) {
        Call<Void> call = api.deleteBurger(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onDeleteSuccess();
                } else {
                    listener.onDeleteError("No se pudo eliminar");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteError("Error de conexión");
            }
        });
    }
}
