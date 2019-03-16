package com.train.amm.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import com.train.amm.LocalMusicAcitivity;
import com.train.amm.imp.LocalMusicControl;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class LocalMusicService extends Service {
    MediaPlayer mediaPlayer;
    private Timer timer;

    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * {@link IBinder} is usually for a complex interface
     * that has been <a href="{@docRoot}guide/components/aidl.html">described using
     * aidl</a>.
     *
     * <p><em>Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process</em>.  More information about the main thread can be found in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
     * Threads</a>.</p>
     *
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new MyMusicControl();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //创建MediaPlayer对象
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止播放
        mediaPlayer.stop();
        //释放占用的资源，此时,mediaPlayer对象销毁
        mediaPlayer.release();
        mediaPlayer = null;

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    class MyMusicControl extends Binder implements LocalMusicControl {

        @Override
        public void play() {
            LocalMusicService.this.play();
        }

        @Override
        public void pause() {
            LocalMusicService.this.pause();
        }

        @Override
        public void playAgain() {
            LocalMusicService.this.continuePlay();
        }

        @Override
        public void seekTo(int progress) {
            LocalMusicService.this.seekTo(progress);
        }
    }

    public void play() {
        //重置
        mediaPlayer.reset();
        try {
            //加载多媒体资源
            mediaPlayer.setDataSource("sdcard/tkdr.mp3");
            mediaPlayer.prepare();
            mediaPlayer.start();

            addTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playNetMusic() {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource("192.168.6.238:8080/tkdr.mp3");
            //异步准备
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //监听加载完成后，开始播放
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void continuePlay() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void seekTo(int progress){
        mediaPlayer.seekTo(progress);
    }

    public void addTimer() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //获取歌曲总时长
                    int duration = mediaPlayer.getDuration();
                    //获取当前的时长
                    int currentPosition = mediaPlayer.getCurrentPosition();

                    Message msg = LocalMusicAcitivity.handler.obtainMessage();

                    //把消息进度封装在bundle对象中
                    Bundle bundle = new Bundle();
                    bundle.putInt("duration", duration);
                    bundle.putInt("currentPosition", currentPosition);
                    msg.setData(bundle);

                    LocalMusicAcitivity.handler.sendMessage(msg);
                }
                //开始计时后5毫秒后开始第一次执行run，之后每500毫秒执行一次
            }, 5, 500);
        }

    }
}
