package com.train.amm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.train.amm.service.RecordService;

public class RecorderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);
    }

    public void startRecord(View view){
        Intent intent = new Intent(this, RecordService.class);
        startService(intent);
    }
}


