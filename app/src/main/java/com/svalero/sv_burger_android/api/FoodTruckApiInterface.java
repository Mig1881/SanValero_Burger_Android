package com.svalero.sv_burger_android.api;

import com.svalero.sv_burger_android.domain.FoodTruck;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodTruckApiInterface {
    // Asumo que tu endpoint en SpringBoot es /foodtrucks o similar
    @GET("foodtrucks")
    Call<List<FoodTruck>> getFoodTrucks();
}
