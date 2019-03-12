package com.train.amm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//从网络查看图片，带缓存
public class NetImageCacheActivity extends AppCompatActivity {
    static ImageView iv;
    static NetImageCacheActivity ma;
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
        setContentView(R.layout.activity_net_image_cache);

        ma = this;
        iv = findViewById(R.id.iv_image);
    }

    public void downLoad(View v) {
        //1.确定网址
        final String path = "http://192.168.6.238:8080/cat.jpg";
        final File file = new File(getCacheDir(),getSourceName(path));
        if(file.exists()){
            //read cache
            Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
            iv.setImageBitmap(bmp);
        }else{
            //download from net
            Thread t = new Thread() {
                @Override
                public void run() {
                    //下载图片
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

                            //缓存到本地文件夹

                            FileOutputStream fos = new FileOutputStream(file);
                            byte[] bys = new byte[1024];
                            int len = 0;
                            while((len = is.read(bys)) != -1){
                                fos.write(bys,0,len);
                            }
                            fos.close();

                            // Bitmap bm = BitmapFactory.decodeStream(is);
                            Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
                            System.out.println("kjdslkfjksjdfjdsa jflk");
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

    //截取文件名字，用于缓存
    public String getSourceName(String path){
        int inedex = path.lastIndexOf("/");

        return path.substring(inedex + 1);
    }
}
