package com.train.amm;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;

import com.train.amm.domain.SystemMessage;
import com.train.amm.utils.Utils;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SystemMessageActivity extends AppCompatActivity {
    List<SystemMessage> smsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_message);
        //需要权限
        // android.permission.READ_SMS

        smsList = new ArrayList<SystemMessage>();

    }

    //查询系统短信
    public void showMessage(View view) {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse("content://sms"), new String[]{"address", "body", "type", "date"}, null, null, null);
        while (cursor.moveToNext()) {
            String address = cursor.getString(cursor.getColumnIndex("address"));
            long date = cursor.getLong(cursor.getColumnIndex("date"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            //System.out.println("address: " + address + " " + "date: " + date + " " + "body: " + body + " " + "type: " + type);
            SystemMessage systemMessage = new SystemMessage(address, type, body, date);
            smsList.add(systemMessage);
        }
    }

    //备份系统短信
    public void backupMessage(View view) {
        //先获取再备份
        showMessage(view);
        //把短信用xml格式保存在sd卡
        XmlSerializer xmlSerializer = Xml.newSerializer();
        File file = new File("sdcard/bak_message.xml");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            xmlSerializer.setOutput(fos, "utf-8");
            xmlSerializer.startDocument("utf-8", true);
            xmlSerializer.startTag(null, "message");

            for (SystemMessage systemMessage : smsList) {
                xmlSerializer.startTag(null, "sms");

                xmlSerializer.startTag(null, "address");
                xmlSerializer.text(systemMessage.getAddress());
                xmlSerializer.endTag(null, "address");

                xmlSerializer.startTag(null, "body");
                xmlSerializer.text(systemMessage.getBody());
                xmlSerializer.endTag(null, "body");

                xmlSerializer.startTag(null, "date");
                xmlSerializer.text(systemMessage.getDate() + "");
                xmlSerializer.endTag(null, "date");

                xmlSerializer.startTag(null, "type");
                xmlSerializer.text(systemMessage.getType());
                xmlSerializer.endTag(null, "type");

                xmlSerializer.endTag(null, "sms");
            }

            xmlSerializer.endTag(null, "message");
            xmlSerializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //插入短信
    public void insertMessage(View view) {
        //代码可以执行但是无效果
        //android4.4以后只支持系统短信进行操作
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ContentResolver contentResolver = getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put("address", 95555);
                contentValues.put("body", "进账8888888888元");
                contentValues.put("date", System.currentTimeMillis());
                contentValues.put("type", 1);
                contentResolver.insert(Uri.parse("content://sms"), contentValues);
                System.out.println("insert success");
                super.run();
            }
        }.start();
    }
}
