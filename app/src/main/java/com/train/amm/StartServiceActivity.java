package com.train.amm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.train.amm.imp.MidMan;
import com.train.amm.service.MyService;
import com.train.amm.utils.Utils;

public class StartServiceActivity extends AppCompatActivity {

    private Intent intent;
    private MyConnetion myConnetion;
    MidMan myBind ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);

        intent = new Intent(this, MyService.class);
        myConnetion = new MyConnetion();

        //bindMethodService方法需要在此处预先绑定
        bindService(intent,myConnetion,BIND_AUTO_CREATE);
    }

    public void startService(View view) {
        //开启服务
        startService(intent);
        Utils.showShortToast(view.getContext(), "startservice");
    }

    public void stopService(View view) {
        //关闭服务

        stopService(intent);
        Utils.showShortToast(view.getContext(), "stopservice");
    }

    public void bindService(View view) {
        //绑定服务
        bindService(intent,myConnetion,BIND_AUTO_CREATE);
    }

    public void unbindService(View view){
        //解绑服务
        unbindService(myConnetion);
    }

    public void bindMethodService(View view){
        //通过此方法访问servie里的方法

        myBind.mid();
        
    }

    class MyConnetion implements ServiceConnection{

        /**
         * Called when a connection to the Service has been established, with
         * the {@link IBinder} of the communication channel to the
         * Service.
         *
         * <p class="note"><b>Note:</b> If the system has started to bind your
         * client app to a service, it's possible that your app will never receive
         * this callback. Your app won't receive a callback if there's an issue with
         * the service, such as the service crashing while being created.
         *
         * @param name    The concrete component name of the service that has
         *                been connected.
         * @param service The IBinder of the Service's communication channel,
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBind = (MidMan) service;
        }

        /**
         * Called when a connection to the Service has been lost.  This typically
         * happens when the process hosting the service has crashed or been killed.
         * This does <em>not</em> remove the ServiceConnection itself -- this
         * binding to the service will remain active, and you will receive a call
         * to {@link #onServiceConnected} when the Service is next running.
         *
         * @param name The concrete component name of the service whose
         *             connection has been lost.
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
