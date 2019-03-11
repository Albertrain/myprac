package com.train.amm;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

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
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory(), "login.txt");
            if (file.exists()) {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line = br.readLine();
                    String[] split = line.split("---");
                    String username = split[0];
                    String password = split[1];

                    textAccount.setText(username);
                    textPassword.setText(password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(this, Environment.getExternalStorageState(), Toast.LENGTH_LONG).show();
        }
    }

    public void login(View view) {
        String userName = textAccount.getText().toString();
        String passWord = textPassword.getText().toString();

        //判断checkbox状态,若勾选，将数据写入本地存储
        CheckBox checkBox = findViewById(R.id.cb_all);
        if (checkBox.isChecked()) {
            //文件位置： data/data/com.train.amm/login.txt
            //File file = new File("data/data/com.train.amm/login.txt");
            //可以用API获取
            //File file = new File(getFilesDir(),"login.txt");
            //File file = new File("sdcard/login.txt");
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(Environment.getExternalStorageDirectory(), "login.txt");
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    fos.write((userName + "---" + passWord).getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Toast.makeText(this, Environment.getExternalStorageState(), Toast.LENGTH_LONG).show();
            }
        }

        // System.out.println("login success!");
        //使用Toast
        Toast.makeText(this, "login success!", Toast.LENGTH_LONG).show();


    }
}
