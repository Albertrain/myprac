package com.train.amm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PiantActivity extends AppCompatActivity {

    private ImageView imageView;
    private Paint paint;
    private Canvas canvas;
    int startX;
    int StartY;
    private Bitmap bitmapCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piant);

        //要在背景bg上作画，先加载bg
        Bitmap bitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        //创建图片的副本
        bitmapCopy = bitmapSrc.createBitmap(bitmapSrc.getWidth(), bitmapSrc.getHeight(), bitmapSrc.getConfig());
        //画笔
        paint = new Paint();
        paint.setStrokeWidth(5);
        //画板
        canvas = new Canvas(bitmapCopy);
        //绘制
        canvas.drawBitmap(bitmapSrc, new Matrix(), paint);

        imageView = findViewById(R.id.iv_draw_panel);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_UP:
                        System.out.println("离开屏幕");
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        System.out.println("按下屏幕");
                        startX = (int) event.getX();
                        StartY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        canvas.drawLine(startX, StartY, x, y, paint);
                        imageView.setImageBitmap(bitmapCopy);

                        //每次绘制完毕后，上一次的结束坐标成为下一次的开始坐标
                        startX = x;
                        StartY = y;
                        System.out.println("滑动");
                        break;
                }

                //返回true是告诉系统，这个触摸事件由我来处理
                //false，告诉系统，这个事件我不会处理，这时，系统会把事件传递给imageView的父节点
                return true;
            }
        });
    }

    //改变画笔颜色红色
    public void changeRed(View view) {
        paint.setColor(Color.RED);
    }

    //改变画笔颜色绿色
    public void changeGreen(View view) {
        paint.setColor(Color.GREEN);
    }

    //改变画笔粗细程度
    public void changeBrush(View view) {
        paint.setStrokeWidth(10);
    }

    public void savePicture(View view) {
        File file = new File("sdcard/test.png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bitmapCopy.compress(Bitmap.CompressFormat.PNG, 100, fos);

        /**
         * 系统每次收到SD卡就绪广播时，都会去遍历sd卡的所有文件和文件夹，把遍历到的所有多媒体文件都在MediaStore数据库保存一个索引，这个索引包含多媒体文件的文件名、路径、大小
         * * 图库每次打开时，并不会去遍历sd卡获取图片，而是通过内容提供者从MediaStore数据库中获取图片的信息，然后读取该图片
         * * 系统开机或者点击加载sd卡按钮时，系统会发送sd卡就绪广播，我们也可以手动发送就绪广播
         */


        //因为Android4.4中限制了系统应用才有权限使用广播通知系统扫描SD卡，所以会抛题目异常。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//如果是4.4及以上版本
            Intent mediaScanIntent = new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(Environment.getExternalStorageDirectory()); //out is your output file
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);
        } else {
            sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://"
                            + Environment.getExternalStorageDirectory())));
        }
    }
}
