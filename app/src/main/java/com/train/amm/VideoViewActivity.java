package com.train.amm;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * vitamio使用，失败
 */
public class VideoViewActivity extends AppCompatActivity {

    public VideoView videoView;
    public static String path = "http://gslb.miaopai.com/stream/oxX3t3Vm5XPHKUeTS-zbXA__.mp4";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        //Vitamio.isInitialized(this);
        //检测是否支持vitamio
        //在清单文件中配置这个activity
        //if (!LibsChecker.checkVitamioLibs(this)) {return;}  旧版本
       // Vitamio.isInitialized(this);

        videoView = findViewById(R.id.vv_player);
        button = findViewById(R.id.bt_viewPlayer);

        videoView.setVideoPath("sdcard/ykhb.mp4");
        videoView.start();




        // videoView.setVideoPath("sdcard/ykhb.mp4");
        //videoView.setVideoPath(path);
        //System.out.println("before start ");
        //videoView.start();

        //playfunction();


    }


    public void startPlay(){
        videoView.setVideoPath(path);
        //playfunction();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                System.out.println("lai le  lai le lai le ");
                videoView.start();
            }
        });

    }

    void playfunction() {
        String path = "";
//		path = "http://gslb.miaopai.com/stream/oxX3t3Vm5XPHKUeTS-zbXA__.mp4";
        path = "http://gslb.miaopai.com/stream/oxX3t3Vm5XPHKUeTS-zbXA__.mp4";
        videoView = findViewById(R.id.vv_player);
        if (path == "") {
            // Tell the user to provide a media file URL/path.
            Toast.makeText(VideoViewActivity.this, "路径错误", Toast.LENGTH_LONG)
                    .show();
            return;
        } else {
            Toast.makeText(VideoViewActivity.this, "路径正确", Toast.LENGTH_LONG)
                    .show();
            videoView.setVideoPath(path);
            //videoView.setMediaController(new MediaController(this));
            //videoView.requestFocus();
            videoView.start();

//            videoView
//                    .setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                        @Override
//                        public void onPrepared(MediaPlayer mp) {
//                            mp.setPlaybackSpeed(1.0f);
//                        }
//                    });
        }
    }

}
