
package com.train.amm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CallbackActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		ListView lv = (ListView) findViewById(R.id.lv);

		final String[] objects = new String[]{
				"免谈，没戏，滚犊子",
				"媳妇我错了，求原谅",
				"老子才是一家之主"
		};
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_listview_finish, R.id.tv, objects));

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Intent data = new Intent();
				data.putExtra("name", objects[position]);

				setResult(2, data);
				finish();

			}
		});
	}
}
