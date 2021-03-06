package com.train.amm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//从网络下载图片
public class NetImageActivity extends AppCompatActivity {
    static ImageView iv;
    static NetImageActivity ma;
    static Handler handler = new Handler() {
        //此方法在主线程中调用，可以用来刷新ui
        public void handleMessage(android.os.Message msg) {
            //处理消息时，需要知道到底是成功的消息，还是失败的消息
            switch (msg.what) {
                case 1:
                    //把位图对象显示至imageview
                    iv.setImageBitmap((Bitmap) msg.obj);
                    break;

                case 0:
                    Toast.makeText(ma, "请求失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_image);
        ma = this;
        iv = findViewById(R.id.iv_image);
    }

    public void downLoad(View v) {
        Thread t = new Thread() {
            @Override
            public void run() {
                //下载图片
                //1.确定网址
                String path = "http://192.168.6.238:8080/cat.jpg";
                try {
                    //2.把网址封装成一个url对象
                    URL url = new URL(path);
                    //3.获取客户端和服务器的连接对象，此时还没有建立连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //4.对连接对象进行初始化
                    //设置请求方法，注意大写
                    conn.setRequestMethod("GET");
                    //设置连接超时
                    conn.setConnectTimeout(5000);
                    //设置读取超时
                    conn.setReadTimeout(5000);
                    //5.发送请求，与服务器建立连接
                    conn.connect();
                    //如果响应码为200，说明请求成功
                    if (conn.getResponseCode() == 200) {
                        //获取服务器响应头中的流，流里的数据就是客户端请求的数据
                        InputStream is = conn.getInputStream();
                        //读取出流里的数据，并构造成位图对象
                        Bitmap bm = BitmapFactory.decodeStream(is);

//						ImageView iv = (ImageView) findViewById(R.id.iv);
//						//把位图对象显示至imageview
//						iv.setImageBitmap(bm);

                        Message msg = new Message();
                        //消息对象可以携带数据
                        msg.obj = bm;
                        msg.what = 1;
                        //把消息发送至主线程的消息队列
                        handler.sendMessage(msg);

                    } else {
//						Toast.makeText(MainActivity.this, "请求失败", 0).show();

                        Message msg = handler.obtainMessage();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }
}