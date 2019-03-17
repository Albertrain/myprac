package com.train.amm;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ContentObserverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_observer);
        //注册一个内容观察者，监听短信数据库内容的改变
        ContentResolver cr = getContentResolver();
        //uri:监听哪个uri上的内容提供者的通知
        //notifyForDescendents:如果是true，那么只要以content://sms开头的uri的数据改变，都能收到通知，比如content://sms/inbox
        cr.registerContentObserver(Uri.parse("content://sms"), true, new MyObserver(new Handler()));
    }


    class MyObserver extends ContentObserver {

        public MyObserver(Handler handler) {
            super(handler);
        }

        //收到数据改变的通知，此方法调用
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            System.out.println("短信数据库改变");
        }

    }

    public void observerForMyProvider(View view) {
        getContentResolver().registerContentObserver(Uri.parse("content://com.train.pprovider"), true,
                new ContentObserver(new Handler()) {
                    @Override
                    public void onChange(boolean selfChange) {
                        super.onChange(selfChange);
                        System.out.println("MyProvider测试类项目的数据库改变了");
                    }
                });
    }

}
