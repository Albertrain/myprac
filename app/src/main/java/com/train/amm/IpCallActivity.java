package com.train.amm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IpCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_call);
    }

    //打电话
    public void ipCall(View view){
        EditText editText = findViewById(R.id.et_call);
        String number = editText.getText().toString();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }

    //将输入的IP号码保存到本机
    public void ipCall2(View view){
        EditText editText = findViewById(R.id.et_call1);
        SharedPreferences sharedPreferences = getSharedPreferences("ip", MODE_PRIVATE);
        sharedPreferences.edit().putString("ipNumber",editText.getText().toString()).commit();

    }

    //清空输入的IP号码，恢复默认电话功能
    public void clearIp(View view){
        SharedPreferences ipNumber = getSharedPreferences("ip", MODE_PRIVATE);
        ipNumber.edit().clear().commit();
    }
}
