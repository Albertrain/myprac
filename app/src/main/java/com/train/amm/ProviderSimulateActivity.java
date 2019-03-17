package com.train.amm;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.train.amm.utils.Utils;

public class ProviderSimulateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_simulate);
    }

    //通过provider进行增加操作
    public void insertPro(View view) {
        //通过内容提供者，把数据插入数据库
        //获取contentresolver对象
        ContentResolver contentResolver = getContentResolver();

        ContentValues contentValues = new ContentValues();
//
        //contentValues.put("phone", "66666");

        //uri:内容提供者的主机名
        //values要插入的数据
        //为第一个表所用的
//        contentResolver.insert(Uri.parse("content://com.train.pprovider"), contentValues);
//
//        contentValues.put("name", "pdtest002");
//        contentValues.put("phone", "2222");
//        contentValues.put("salary", "22223");
//        contentResolver.insert(Uri.parse("content://com.train.pprovider"), contentValues);
//
//        contentValues.put("name", "pdtest003");
//        contentValues.put("phone", "33333");
//        contentValues.put("salary", "33333");
//        contentResolver.insert(Uri.parse("content://com.train.pprovider"), contentValues);

        //第二个student表所用
        contentValues.put("salary",2222);
       // contentValues.put("name","studentprovider");
        contentValues.put("name","observer");
        contentResolver.insert(Uri.parse("content://com.train.pprovider/student"), contentValues);
    }

    //通过provider进行删除操作
    public void deletePro(View view) {
        //获取contentresolver对象
        ContentResolver contentResolver = getContentResolver();

        int delete = contentResolver.delete(Uri.parse("content://com.train.pprovider"), "name = ?", new String[]{"providertest"});
        Utils.showShortToast(getApplicationContext(), delete + "");
    }

    //通过provider进行修改操作
    public void updatePro(View view) {
        //获取contentresolver对象
        ContentResolver contentResolver = getContentResolver();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", "updateprovider");
        int update = contentResolver.update(Uri.parse("content://com.train.pprovider"), contentValues, "name = ?", new String[]{"pdtest002"});
        Utils.showShortToast(getApplicationContext(), update + "");

    }

    //通过provider进行查询操作
    public void searchPro(View view){
        //获取contentresolver对象
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse("content://com.train.pprovider/person/4"), null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String salary = cursor.getString(cursor.getColumnIndex("salary"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            Utils.showShortToast(getApplicationContext(),"name: "+ name +" "+"salary: "+ salary + " " + "phone: " + phone);
        }
    }
}
