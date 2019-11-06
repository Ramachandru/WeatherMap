package com.example.weathermap.ui;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.weathermap.ui.api.API;
import com.example.weathermap.ui.model.weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataWorker extends Worker {

    public static String APIKEY = "3724d0dc267ceb618e5873b25b527f79";
    public static String id = "77.580643";
    Context mContext;

    public DataWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext = context;
    }

    @Override
    public Result doWork() {
        Log.v("","DOING WORK FROM WORKER....");
        getResponseFrmServer();
        return Worker.Result.success();
    }

    public void getResponseFrmServer() {

        API apiData = WebService.getRetrofitInstance().create(API.class);
        Call<weather> call = apiData.getCurrentWeatherData(id, APIKEY);
        call.enqueue(new Callback<weather>() {
            @Override
            public void onResponse(@NonNull Call<weather> call, @NonNull Response<weather> response) {
                if (response.code() == 200) {
                    weather lweather = response.body();
                    StringBuilder builder=new StringBuilder();
                    builder.append(lweather.humidity)
                            .append("\n")
                            .append(lweather.dt)
                            .append("\n")
                            .append(lweather.tmp);
                   // MainActivity.activityMainBinding.fetchText.setText(builder);
                    Worker.Result.success();
                }
            }

            @Override
            public void onFailure(@NonNull Call<weather> call, @NonNull Throwable t) {
              //  MainActivity.activityMainBinding.fetchText.setText(t.getMessage());
                Worker.Result.failure();
            }
        });
    }
}
