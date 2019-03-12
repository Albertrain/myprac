package com.train.amm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

/**
 * 数据库测试类
 */
public class MyTest {

    private Context mTargetContext;
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writableDatabase;

    @Before
    public void setUp() throws Exception {
        //获取虚拟上下文
        mTargetContext = InstrumentationRegistry.getTargetContext();

        myOpenHelper = new MyOpenHelper(mTargetContext, "people.db", null, 1);
        //若数据库不存在，创建数据库并获得一个可写数据库对象，否则直接获取数据库对象
        writableDatabase = myOpenHelper.getWritableDatabase();
    }


    public void test() {

        myOpenHelper = new MyOpenHelper(mTargetContext, "people.db", null, 1);
        //若数据库不存在，创建数据库并获得一个可写数据库对象，否则直接获取数据库对象
        writableDatabase = myOpenHelper.getWritableDatabase();
    }

    @Test
    public void insert() {
        myOpenHelper = new MyOpenHelper(mTargetContext, "people.db", null, 1);
        //若数据库不存在，创建数据库并获得一个可写数据库对象，否则直接获取数据库对象
        writableDatabase = myOpenHelper.getWritableDatabase();
        //writableDatabase.execSQL("insert into person(name,salary,phone)values('Albert','15000',13856567878)");
        writableDatabase.execSQL("insert into person(name,salary,phone)values(?,?,?)", new Object[]{"Albert", "15000", 138138});
        writableDatabase.execSQL("insert into person(name,salary,phone)values(?,?,?)", new Object[]{"Alice", "16000", 132132});
        writableDatabase.execSQL("insert into person(name,salary,phone)values(?,?,?)", new Object[]{"Amy", "15555", 131131});
        writableDatabase.close();
    }

    @Test
    public void delete() {
        myOpenHelper = new MyOpenHelper(mTargetContext, "people.db", null, 1);
        //若数据库不存在，创建数据库并获得一个可写数据库对象，否则直接获取数据库对象
        writableDatabase = myOpenHelper.getWritableDatabase();
        writableDatabase.execSQL("delete from person where name= ?", new Object[]{"Amy"});
        writableDatabase.close();
    }

    @Test
    public void update() {
        myOpenHelper = new MyOpenHelper(mTargetContext, "people.db", null, 1);
        //若数据库不存在，创建数据库并获得一个可写数据库对象，否则直接获取数据库对象
        writableDatabase = myOpenHelper.getWritableDatabase();
        writableDatabase.execSQL("update person set phone = ? where name = ?", new Object[]{"155155", "Alice"});
        writableDatabase.close();
    }

    @Test
    public void select() {
        myOpenHelper = new MyOpenHelper(mTargetContext, "people.db", null, 1);
        //若数据库不存在，创建数据库并获得一个可写数据库对象，否则直接获取数据库对象
        writableDatabase = myOpenHelper.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select name,salary from person", null);
        while (cursor.moveToNext()) {
            //String name = cursor.getString(0);
            //通过列索引获取列的值
            String name = cursor.getString(cursor.getColumnIndex("name"));

            //String salary = cursor.getString(1);
            String salary = cursor.getString(cursor.getColumnIndex("salary"));
            System.out.println(name + "---" + salary);
        }

        writableDatabase.close();
    }


    //用Android封装好的db方法
    @Test
    public void insertApi() {
        //把要插入db的数据封装入ContentValue对象
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "one");
        contentValues.put("phone", "121121");
        contentValues.put("salary", "17000");

        writableDatabase.insert("person", null, contentValues);
    }

    @Test
    public void insertApi_1() {
        for (int i = 1; i <=60; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "person"+i);
            contentValues.put("phone", "121"+i+i);
            contentValues.put("salary", "170"+i+i);
            writableDatabase.insert("person", null, contentValues);
        }
    }

    @Test
    public void deleteApi() {
        int person = writableDatabase.delete("person", "name =? and _id = ?", new String[]{"Albert", "2"});
        System.out.println(person);
    }


    @Test
    public void updateApi() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "Amy");

        int pseron = writableDatabase.update("person", contentValues, "_id = ?", new String[]{"5"});
        System.out.println(pseron);
    }

    @Test
    public void selectApi() {
        Cursor cursor = writableDatabase.query("person", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String salary = cursor.getString(cursor.getColumnIndex("salary"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            System.out.println("name: " + name + " " + "salary: " + salary + " " + "phone: " + phone);
        }
    }

    //事务
    @Test
    public void transition() {
        try {
            //开启事务
            writableDatabase.beginTransaction();
            ContentValues contentValues = new ContentValues();

            contentValues.put("salary", 16000);
            writableDatabase.update("person", contentValues, "name = ?", new String[]{"Albert"});

            //contentValues.clear();
            contentValues.put("salary", 15000);
            writableDatabase.update("person", contentValues, "name = ?", new String[]{"Alice"});

            //设置事务执行成功
            writableDatabase.setTransactionSuccessful();
            System.out.println("success");
        } finally {
            //关闭事务,同时提交，如果已经设置事务执行成功，则sql语句生效，否则回滚
            writableDatabase.endTransaction();
            System.out.println("close");
        }

    }
}
