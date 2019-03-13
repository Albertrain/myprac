package com.train.amm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

    }

    public void show1(View c) {
        //跳转至选择联系人Activity
        Intent intent = new Intent(this, ContactActivity.class);
//		startActivity(intent);
        //用这个api启动的Activity，在销毁时，系统会回调onActivityResult
        startActivityForResult(intent, 10);
    }


    public void show2(View v) {
        //跳转至选择快捷回复的Activity
        Intent intent = new Intent(this, CallbackActivity.class);
        startActivityForResult(intent, 20);
    }

    //如果有Activity在销毁时返回了数据，那么就会调用此方法来接收数据
    //requestCode:用来区分数据来自于哪一个Activity
    //resultCode:用来区分返回的数据是什么类型的
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        String name = data.getStringExtra("name");
        if (requestCode == 10) {
            EditText et = (EditText) findViewById(R.id.et);
            et.setText(name);
        } else if (requestCode == 20) {
            EditText et_content = (EditText) findViewById(R.id.et_content);
            et_content.setText(name);
        }
    }


}
