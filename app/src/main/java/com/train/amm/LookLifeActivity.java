package com.train.amm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Activity生命周期
 * void onCreate()
 * Activity已经被创建完毕
 *
 * void onStart()
 * Activity已经显示在屏幕，但没有得到焦点
 *
 * void onResume()
 * Activity得到焦点，可以与用户交互
 *
 * void onPause()
 * Activity失去焦点，无法再与用户交互，但依然可见
 *
 * void onStop()
 * Activity不可见，进入后台
 *
 * void onDestroy()
 * Activity被销毁
 *
 * void onRestart()
 * Activity从不可见变成可见时会执行此方法
 *
 * 使用场景
 * Activity创建时需要初始化资源，销毁时需要释放资源；或者播放器应用，在界面进入后台时需要自动暂停
 * 完整生命周期（entire lifetime）
 * onCreate-->onStart-->onResume-->onPause-->onStop-->onDestory
 * 可视生命周期（visible lifetime）
 * onStart-->onResume-->onPause-->onStop
 * 前台生命周期（foreground lifetime）
 * onResume-->onPause
 */
public class LookLifeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_life);
        //Activity已经被创建完毕
        System.out.println("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Activity已经显示在屏幕，但没有得到焦点
        System.out.println("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Activity得到焦点，可以与用户交互
        System.out.println("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Activity失去焦点，无法再与用户交互，但依然可见
        System.out.println("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Activity不可见，进入后台
        System.out.println("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity被销毁
        System.out.println("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Activity从不可见变成可见时会执行此方法
        System.out.println("onRestart");
    }


    //此方法跳转进入第二个activity，观察当前activity的方法调用情况
    public void lookLife(View view){
        Intent intent = new Intent(this,LookLifeActivity2.class);
        startActivity(intent);
    }


    //启动模式的Activity
    public void startMode(View view){

    }
}
