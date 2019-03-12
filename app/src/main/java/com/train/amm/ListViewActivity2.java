package com.train.amm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.train.amm.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义一个listview，显示数据库中的数据
 */
public class ListViewActivity2 extends AppCompatActivity {
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
                Person person = personList.get(position);
                //自定义listview显示数据库条目

                //获取布局填充器对象
//			LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//			使用布局填充器填充布局文件
//			View v2 = inflater.inflate(R.layout.item_listview, null);

//			LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//			View v3 = inflater2.inflate(R.layout.item_listview, null);

                //通过资源id查找组件,注意调用的是View对象的findViewById
                //优化
                View view = null;
                if(convertView == null){
                    view= View.inflate(getApplicationContext(), R.layout.activity_list_view2, null);
                }else{
                    view = convertView;
                }

                TextView tv_name = view.findViewById(R.id.tv_name);
                tv_name.setText(person.getName());

                TextView tv_salary = view.findViewById(R.id.tv_salary);
                tv_salary.setText(person.getSalary());

                TextView tv_phone = view.findViewById(R.id.tv_phone);
                tv_phone.setText(person.getPhone());

                return view;
            }
        });
    }


}
