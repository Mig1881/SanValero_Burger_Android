package com.svalero.sv_burger_android.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodTruckApi {
    public static final String BASE_URL = "http://10.0.2.2:8080/";

    private static FoodTruckApiInterface instance;

    public static FoodTruckApiInterface buildInstance() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(FoodTruckApiInterface.class);
        }
        return instance;
    }
}
