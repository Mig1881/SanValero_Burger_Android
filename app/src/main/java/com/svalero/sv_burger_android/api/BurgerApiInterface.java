package com.svalero.sv_burger_android.api;

import com.svalero.sv_burger_android.domain.Burger;
import com.svalero.sv_burger_android.domain.BurgerInDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BurgerApiInterface {

    @GET("burgers")
    Call<List<Burger>> getBurgers();
    @POST("burgers")
    Call<Burger> addBurger(@Body BurgerInDto burger);

}
