package com.train.amm;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.PSSParameterSpec;

public class LoginActivity_01 extends AppCompatActivity {

    private EditText textAccount;
    private EditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取用户名和密码
        textAccount = findViewById(R.id.username);
        textPassword = findViewById(R.id.password);

        readAccout();
    }

    public void readAccout() {
        //File file = new File("data/data/com.train.amm/login.txt");
        //File file = new File(getFilesDir(),"login.txt");
        //File file = new File("sdcard/login.txt");

        SharedPreferences sharedPreferences = getSharedPreferences("login.", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String password = sharedPreferences.getString("password","");
        textAccount.setText(name);
        textPassword.setText(password);

    }


    public void login(View view) {
        String userName = textAccount.getText().toString();
        String passWord = textPassword.getText().toString();

        //判断checkbox状态,若勾选，将数据写入本地存储
        CheckBox checkBox = findViewById(R.id.cb_all);
        if (checkBox.isChecked()) {
            //用sharedpreference保存数据
            SharedPreferences sharedPreferences = getSharedPreferences("login.", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("name", userName);
            edit.putString("password", passWord);
            edit.commit();

        } else {
            Toast.makeText(this, Environment.getExternalStorageState(), Toast.LENGTH_LONG).show();
        }
    }


}

