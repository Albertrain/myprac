package com.train.amm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 数据库创建时此方法调用
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("datebase create");
        db.execSQL("create table person (_id integer primary key autoincrement,name char(10),salary char(20),phone integer(20))");
    }

    /**
     * 数据库升级时，此方法调用
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("database update");
    }
}
