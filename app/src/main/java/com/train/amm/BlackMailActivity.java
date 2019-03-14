package com.train.amm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BlackMailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_mail);
    }

    //禁用返回键

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
    }
}
