package com.train.amm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		ListView lv = (ListView) findViewById(R.id.lv);

		final String[] objects = new String[]{
				"小志",
				"逼哥",
				"世界级XXX",
				"国服第一"
		};

		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_listview_finish, R.id.tv, objects));

		//给listview设置条目的点击侦听
		lv.setOnItemClickListener(new OnItemClickListener() {

			//当某个条目被点击时，此方法调用
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {

				//Activity返回时传递数据，也是通过意图对象
				Intent data = new Intent();
				//把要传递的数据封装至意图对象中
				data.putExtra("name", objects[position]);

				//当前Activity销毁时，data这个意图就会传递给启动当前Activity的那个Activity
				setResult(1, data);

				//销毁当前Activity
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
}
