package com.example.weathermap.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.weathermap.R;
import com.example.weathermap.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // databinding for views
     ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("","STARTED.... ...");
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        activityMainBinding.fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("","CLICKED.... ...");
                Constraints constraints = new Constraints.Builder()
                        .setRequiresDeviceIdle(true)
                        .setRequiresCharging(true)
                        .build();
                WorkManager mWorkManager = WorkManager.getInstance();
                PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(DataWorker.class, 2, TimeUnit.HOURS)
                        .setConstraints(constraints)
                        .build();
                mWorkManager.enqueue(periodicWork);
                Log.v("","DOING ...");
            }
        });
    }


}
