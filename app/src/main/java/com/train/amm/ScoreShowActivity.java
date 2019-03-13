package com.train.amm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class ScoreShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_show);

        //从intent对象中取出数据
        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name1");
        String name2 = intent.getStringExtra("name2");

        Random random = new Random();
        int score = random.nextInt(100);
        TextView textView = findViewById(R.id.tv_show_score);
        textView.setText(name1+" 和 " + name2 +" 的友谊值为: " + score);
    }
}
