package com.train.amm;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.train.amm.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class ShowDataActivity extends AppCompatActivity {


    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writableDatabase;
    List<Person> personList;
    private Cursor cursor;

    @SuppressLint("Recycle")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        System.out.println("shoudata in-----m");

        personList = new ArrayList<Person>();

        myOpenHelper = new MyOpenHelper(this, "people.db", null, 1);
        writableDatabase = myOpenHelper.getWritableDatabase();

        cursor = writableDatabase.query("person", null, null, null, null, null, null);
        while ((cursor.moveToNext())) {
            String _id = cursor.getString(0);
            String name = cursor.getString(1);
            String salary = cursor.getString(2);
            String phone = cursor.getString(3);

            Person person = new Person(_id, name, salary, phone);

            personList.add(person);
        }

        LinearLayout viewById = findViewById(R.id.ll);

        for (Person person : personList) {
            //创建显示的textview
            TextView textView = new TextView(this);

            //将person数据设置在textview上
            textView.setText(person.toString());
            textView.setTextSize(16);

            //将textview作为跟布局的节点
            viewById.addView(textView);
        }
    }
}
