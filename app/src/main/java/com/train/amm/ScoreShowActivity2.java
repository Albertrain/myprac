package com.train.amm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;

public class ScoreShowActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_show2);

        //从intent对象中取出数据
        Bundle bundle = getIntent().getExtras();
        String name1 = bundle.getString("name1");
        String name2 = bundle.getString("name2");

        Random random = new Random();
        int score = random.nextInt(100);
        TextView textView = findViewById(R.id.tv_show_score2);
        textView.setText(name1+" 和 " + name2 +" 的友谊值为: " + score);
    }
}
