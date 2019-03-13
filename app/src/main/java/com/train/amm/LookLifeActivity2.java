package com.train.amm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LookLifeActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_life2);
        System.out.println("onCreate2");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart2");
    }
}
