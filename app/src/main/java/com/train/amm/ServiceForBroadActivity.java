package com.train.amm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.train.amm.service.RegisterBroadService;

public class ServiceForBroadActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_for_broad);

        intent = new Intent(this, RegisterBroadService.class);
    }

    public void serviceStartBroad(View view){
        startService(intent);
    }

    public void serviceStopBroad(View view){
        stopService(intent);
    }
}
