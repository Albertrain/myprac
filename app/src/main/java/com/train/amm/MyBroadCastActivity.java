package com.train.amm;

import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyBroadCastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broad_cast);
    }

    public void openMyBroadCast(View view){
        //开启自定义广播
        Intent intent = new Intent();
        intent.setAction("com.train.action");

        sendBroadcast(intent);
    }


    //发送有序广播
    public void orderCast(View view){
        //有序广播可以层层拦截，层层剥夺，修改广播至下级，甚至拦截广播
        Intent intent = new Intent();
        intent.setAction("com.train.order");

        sendOrderedBroadcast(intent,null,null,null,0,"有序广播",null);
    }

    public void resultCast(View view){
        Intent intent = new Intent();
        intent.setAction("com.train.order");

        sendOrderedBroadcast(intent,null,new MyResultReceiver(),null,0,"有序广播",null);
    }

    //最终接收者不需要再清单文件中配置，这个广播接收者只接收该有序广播，且是最后一个收到该广播的
    class MyResultReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String resultData = getResultData();
            System.out.println("最终接收者: " + resultData);
        }
    }
}
