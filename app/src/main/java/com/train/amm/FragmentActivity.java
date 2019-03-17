package com.train.amm;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.train.amm.ui.fragment.Fragment01;
import com.train.amm.ui.fragment.Fragment02;
import com.train.amm.ui.fragment.Fragment03;

public class FragmentActivity extends AppCompatActivity {

    private Fragment03 fragment03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //创建fragment对象
         fragment03 = new Fragment03();
        //获取fragment事务管理器
        FragmentManager fragmentManager = getFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //把内容显示到帧布局
        fragmentTransaction.replace(R.id.fl_layout, fragment03);
        fragmentTransaction.commit();
    }

    public void toFragment1(View view) {
        //创建fragment对象
        Fragment01 fragment01 = new Fragment01();
        //获取fragment事务管理器
        FragmentManager fragmentManager = getFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //把内容显示到帧布局
        fragmentTransaction.replace(R.id.fl_layout, fragment01);
        fragmentTransaction.commit();
    }

    public void toFragment2(View view) {
        //创建fragment对象
        Fragment02 fragment02 = new Fragment02();
        //获取fragment事务管理器
        FragmentManager fragmentManager = getFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //把内容显示到帧布局
        fragmentTransaction.replace(R.id.fl_layout, fragment02);
        fragmentTransaction.commit();
    }

    public void toFragment3(View view) {
        //创建fragment对象
         fragment03 = new Fragment03();
        //获取fragment事务管理器
        FragmentManager fragmentManager = getFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //把内容显示到帧布局
        fragmentTransaction.replace(R.id.fl_layout, fragment03);
        fragmentTransaction.commit();
    }

    public void showInFragment(View view){
        EditText editText = findViewById(R.id.et_fragment);
        String string = editText.getText().toString();
        fragment03.setText(string);
    }

    public void setText(String string){
        EditText editText = findViewById(R.id.et_fragment);
        editText.setText(string);
    }
}
