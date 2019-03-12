package com.train.amm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.train.amm.smartimage.com.loopj.android.image.SmartImageView;

public class SmartImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_image);
    }

    public void smartDown(View view) {
        String path = "http://192.168.6.238:8080/cat.jpg";
        SmartImageView smartImageView = findViewById(R.id.si_image);
        smartImageView.setImageUrl(path);
    }
}
