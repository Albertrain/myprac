package com.train.amm;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.train.amm.utils.Utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlSourceActivity extends Activity {

    Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText((String)msg.obj);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_source);
    }

    public void click(View v){
        Thread t = new Thread(){
            @Override
            public void run() {
                String path = "http://192.168.6.238:8080/zmw.html";
                try {
                    URL url = new URL(path);
                    //获取连接对象，此时还未建立连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    //先建立连接，然后获取响应码
                    if(conn.getResponseCode() == 200){
                        //拿到服务器返回的输入流，流里的数据就是html的源文件
                        InputStream is = conn.getInputStream();
                        //从流里把文本数据取出来
                        String text = Utils.getTextFromStream(is);

                        //发送消息，让主线程刷新ui，显示源文件
                        Message msg = handler.obtainMessage();
                        msg.obj = text;
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
