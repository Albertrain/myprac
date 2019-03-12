package com.train.amm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.train.amm.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writableDatabase;
    List<Person> personList;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


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

        ListView listView = findViewById(R.id.lt);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return personList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText(personList.get(position).toString());
                textView.setTextSize(16);
                textView.setTextColor(-4253158);
                return textView;
            }
        });
    }


}
