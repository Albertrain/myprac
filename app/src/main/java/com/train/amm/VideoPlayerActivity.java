package com.train.amm;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * SurfaceView
 * 双缓冲技术
 * 重量级组件
 * 只要不可见，就不会创建，可见时，才会创建
 * 只要不可见，就会销毁
 */
public class VideoPlayerActivity extends AppCompatActivity {

    private MediaPlayer player;
    static int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        SurfaceView surfaceView = findViewById(R.id.sf_player);
        //拿到SurfaceView控制器
        final SurfaceHolder holder = surfaceView.getHolder();

//        		Thread t = new Thread(){
//			@Override
//			public void run() {
//				try {
//					sleep(200);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						MediaPlayer player = new MediaPlayer();
//						player.reset();
//						try {
//							player.setDataSource("sdcard/2.3gp");
//							player.setDisplay(holder);
//							player.prepare();
//							player.start();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//
//					}
//				});
//
//			}
//		};
//		t.start();


        holder.addCallback(new SurfaceHolder.Callback() {


            //surfaceView销毁时调用
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //每次surfaceview销毁时，同时停止播放视频
                if (player != null) {
                    currentPosition = player.getCurrentPosition();
                    player.stop();
                    player.release();
                    player = null;
                }

            }

            //surfaceView创建时调用
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //每次surfaceView创建时才去播放视频
                if (player == null) {
                    player = new MediaPlayer();
                    player.reset();
                    try {
                        player.setDataSource("sdcard/xajh.mp4");
                        player.setDisplay(holder);
                        player.prepare();
                        player.start();
                        player.seekTo(currentPosition);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            //surfaceView结构改变时调用
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                       int height) {

            }
        });

    }
}
