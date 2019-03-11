package com.train.amm;

import android.app.Activity;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.bt_call);
        button.setOnClickListener(new MyListener());
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //获取edittext的电话号码
            EditText et = findViewById(R.id.et_phonenumber);
            String number = et.getText().toString();

            //获取intent对象
            Intent intent = new Intent();
            //封装打电话的目的到intent对象
            intent.setAction(Intent.ACTION_CALL);
            //设置打给谁
            intent.setData(Uri.parse("tel:" + number));
            //启动封装好的intent动作
            startActivity(intent);
            System.out.println("calling...");
        }
    }

    //定义了控件的onclick后必须定义onclick方法
    public void getClick(View v) {
        System.out.println("getClick works");
    }

    public void sendMessage(View v) {
        //获取到号码和短信内容
        EditText et_number = findViewById(R.id.et_message_number);
        String messageNumber = et_number.getText().toString();

        EditText et_content = findViewById(R.id.et_message_content);
        String messageContent = et_content.getText().toString();

        //获取到短信管理器
        SmsManager sm = SmsManager.getDefault();

        //切割短信，将超长短信分割成为多个短信
        ArrayList<String> message = sm.divideMessage(messageContent);

        //发送短信
        for (String msg : message) {
            sm.sendTextMessage(messageNumber, null, msg, null, null);
            System.out.println(msg);
        }

        System.out.println("send success!");

    }
}
