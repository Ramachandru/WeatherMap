package com.example.weathermap.ui.api;

import com.example.weathermap.ui.model.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    public static String BASEURL = "http://api.openweathermap.org";

    @GET("data/2.5/weather?")
    Call<weather> getCurrentWeatherData(@Query("id") String lat, @Query("APPID") String app_id);

}
