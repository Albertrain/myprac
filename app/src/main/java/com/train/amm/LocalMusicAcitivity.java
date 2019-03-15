package com.train.amm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.train.amm.imp.LocalMusicControl;
import com.train.amm.service.LocalMusicService;

public class LocalMusicAcitivity extends AppCompatActivity {
    LocalMusicControl localMusicControl;
    private Intent intent;
    private MyLocalConnection myLocalConnetion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music_acitivity);

        intent = new Intent(this, LocalMusicService.class);
        startService(intent);
        myLocalConnetion = new MyLocalConnection();
        bindService(intent, myLocalConnetion, BIND_AUTO_CREATE);
    }

    public void playLocalMusic(View view) {
        localMusicControl.play();
    }

    public void continueLocalMusic(View view) {
        localMusicControl.playAgain();
    }

    public void pauseLocalMusic(View view) {
        localMusicControl.pause();
    }

    public void exitLocalMusic(View view){
        //需要在服务里实放MidiaPlayer资源
        unbindService(myLocalConnetion);
        stopService(intent);
    }

    class MyLocalConnection implements ServiceConnection {

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
            localMusicControl = (LocalMusicControl) service;
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
