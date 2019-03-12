package com.train.amm;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.SmsManager;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.bt_call);
        button.setOnClickListener(new MyListener());
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //获取edittext的电话号码
            EditText et = findViewById(R.id.et_phonenumber);
            String number = et.getText().toString();

            //获取intent对象
            Intent intent = new Intent();
            //封装打电话的目的到intent对象
            intent.setAction(Intent.ACTION_CALL);
            //设置打给谁
            intent.setData(Uri.parse("tel:" + number));
            //启动封装好的intent动作
            startActivity(intent);
            System.out.println("calling...");
        }
    }

    //定义了控件的onclick后必须定义onclick方法
    public void getClick(View v) {
        System.out.println("getClick works");
    }

    public void sendMessage(View v) {
        //获取到号码和短信内容
        EditText et_number = findViewById(R.id.et_message_number);
        String messageNumber = et_number.getText().toString();

        EditText et_content = findViewById(R.id.et_message_content);
        String messageContent = et_content.getText().toString();

        //获取到短信管理器
        SmsManager sm = SmsManager.getDefault();

        //切割短信，将超长短信分割成为多个短信
        ArrayList<String> message = sm.divideMessage(messageContent);

        //发送短信
        for (String msg : message) {
            sm.sendTextMessage(messageNumber, null, msg, null, null);
            System.out.println(msg);
        }

        System.out.println("send success!");

    }

    //获取存储卡空间
    public void getSpace(View view) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize;
        long totalBlocks;
        long availableBlocks;

        //获取当前系统版本的等级
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
            totalBlocks = stat.getBlockCountLong();
            availableBlocks = stat.getAvailableBlocksLong();
        } else {
            blockSize = stat.getBlockSize();
            totalBlocks = stat.getBlockCount();
            availableBlocks = stat.getAvailableBlocks();
        }

        TextView tv = (TextView) findViewById(R.id.tv_spaceshow);
        tv.setText(formatSize(availableBlocks * blockSize));
    }

    private String formatSize(long size) {
        return Formatter.formatFileSize(this, size);
    }

    //进入下一个页面
    public void newPage(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LoginActivity_01.class);
        startActivity(intent);
    }

    //备份短信页面
    public void backupMessage(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, BackMessageActivity.class);
        startActivity(intent);
    }

    //显示数据库页面
    public void ShowData(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,ShowDataActivity.class);
        startActivity(intent);
    }

    public void showListView(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,ListViewActivity.class);
        startActivity(intent);
    }

    public void showListView2(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,ListViewActivity2.class);
        startActivity(intent);
    }

    //显示arraylistview和simplelistview的demo
    public void showArrayListview(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,ArrayListViewActivity.class);
        startActivity(intent);
    }

    //显示对话框的demo
    public void ShowDialog(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,ConfirmCancelActivity.class);
        startActivity(intent);
    }

    //显示网络图片
    public void showNetImage(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,NetImageActivity.class);
        startActivity(intent);
    }

    //显示网络图片带缓存
    public void showNetCacheImage(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,NetImageCacheActivity.class);
        startActivity(intent);
    }

    //smartimageview查看网页图片
    public void smartImage(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,SmartImageActivity.class);
        startActivity(intent);
    }


    //查看html源码
    public void htmlSource(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,HtmlSourceActivity.class);
        startActivity(intent);
    }

    //打开新闻页面
    public void openNews(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,NewsClientActivity.class);
        startActivity(intent);
    }
}
