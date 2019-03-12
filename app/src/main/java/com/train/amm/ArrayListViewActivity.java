package com.train.amm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list_view);

        String[] objects = new String[]{
                "小志",
                "小志的儿子",
                "萌萌"
        };

        ListView lv = (ListView) findViewById(R.id.lv_array);
//		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_listview, R.id.tv_name, objects));

        //集合中每个元素都包含ListView条目需要的所有数据,该案例中每个条目需要一个字符串和一个整型,所以使用一个map来封装这两种数据
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("photo", R.drawable.photo1);
        map1.put("name", "小志的儿子");
        data.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("photo", R.drawable.photo2);
        map2.put("name", "小志");
        data.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("photo", R.drawable.photo3);
        map3.put("name", "赵帅哥");
        data.add(map3);

        lv.setAdapter(new SimpleAdapter(this, data, R.layout.item_listview,
                new String[]{"photo", "name"}, new int[]{R.id.iv_photo, R.id.tv_name}));
    }


}


