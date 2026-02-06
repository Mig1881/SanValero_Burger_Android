package com.svalero.sv_burger_android.api;

import com.svalero.sv_burger_android.domain.FoodTruck;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FoodTruckApiInterface {
    // Asumo que tu endpoint en SpringBoot es /foodtrucks o similar
    @GET("foodtrucks")
    Call<List<FoodTruck>> getFoodTrucks();
    @DELETE("foodtrucks/{id}")
    Call<Void> deleteFoodTruck(@Path("id") long id);
    @GET("foodtrucks/{id}")
    Call<FoodTruck> getFoodTruck(@Path("id") long id);
    @POST("foodtrucks")
    Call<FoodTruck> addFoodTruck(@Body FoodTruck foodTruck);
    @PUT("foodtrucks/{id}")
    Call<FoodTruck> modifyFoodTruck(@Path("id") long id, @Body FoodTruck foodTruck);
}
