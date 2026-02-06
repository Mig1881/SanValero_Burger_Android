package com.svalero.sv_burger_android.model;

import com.svalero.sv_burger_android.api.BurgerApi;
import com.svalero.sv_burger_android.api.BurgerApiInterface;
import com.svalero.sv_burger_android.contract.BurgerListContract;
import com.svalero.sv_burger_android.domain.Burger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BurgerListModel implements BurgerListContract.Model {

    @Override
    public void loadBurgers(OnLoadBurgersListener listener) {
        // CAMBIO AQUÍ: Usamos BurgerApi en vez de FoodTruckApi
        BurgerApiInterface api = BurgerApi.buildInstance();
        Call<List<Burger>> call = api.getBurgers();

        call.enqueue(new Callback<List<Burger>>() {
            @Override
            public void onResponse(Call<List<Burger>> call, Response<List<Burger>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onError("Error cargando burgers");
                }
            }

            @Override
            public void onFailure(Call<List<Burger>> call, Throwable t) {
                listener.onError("Error de conexión");
            }
        });
    }
}
