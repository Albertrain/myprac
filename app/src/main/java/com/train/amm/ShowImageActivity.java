package com.train.amm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class ShowImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

    }

    public void showScaleImage(View view){
        //解析图片所用到的参数封装对象
        BitmapFactory.Options options = new BitmapFactory.Options();
        //只得到宽高，不分配内存
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile("sdcard/test.jpg", options);
        //获取图片的宽高
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;

        //获取屏幕宽高
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        int screenWidth = defaultDisplay.getWidth();
        int screenHeight = defaultDisplay.getHeight();

        //计算缩放比例
        int scale = 1;
        int scaleWidth = imageWidth / screenWidth;
        int scaleHeight = imageHeight / screenHeight;
        if(scaleWidth >= scaleHeight && scaleWidth >=1){
            scale = scaleWidth;
        }
        else if(scaleWidth < scaleHeight && scaleHeight >=1){
            scale = scaleHeight;
        }

        //设置缩放比例
        options.inSampleSize= scale;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile("sdcard/test.jpg",options);

        //设置到imageView
        ImageView imageView = findViewById(R.id.iv_image_scale);
        imageView.setImageBitmap(bitmap);

    }


    //创建图片副本
    public void copyImage(View view){
        //这个对象是只读的
        Bitmap bmSrc = BitmapFactory.decodeFile("sdcard/test.jpg");

        //创建图片副本
        //在内存中创建一个和原图一摸一样大小的bitmap对象，创建与原图大小一致的空白画
        Bitmap bmCopy = Bitmap.createBitmap(bmSrc.getWidth(),bmSrc.getHeight(),bmSrc.getConfig());

        //创建画笔对象
        Paint paint = new Paint();

        //创建画板，把创建好的白纸对象铺在画板上
        Canvas canvas = new Canvas(bmCopy);

        //开始绘画，把原图绘制在白纸
        Matrix mt = new Matrix();
        //平移
        //mt.setTranslate(20,30);
        //缩放,以左上角为缩放中心点
        //sx:水平方向缩放比例
        //sy:竖直方向缩放比例
        //mt.setScale(0.5f,0.5f);

        //缩放，以图片中心为缩放中心点
        //mt.setScale(0.5f,0.5f,bmCopy.getHeight()/2,bmCopy.getHeight()/2);

        //旋转,中心点在左上角
        //mt.setRotate(30);

        //镜面效果
        //第二个设置要post，否则不生效
        //mt.setScale(-1,1);
        //mt.postTranslate(bmCopy.getWidth(),0);

        //倒影效果
        //mt.setScale(1,-1);
        //mt.postTranslate(0,bmCopy.getHeight());

        canvas.drawBitmap(bmSrc,new Matrix(),paint);
        //加一个简单特效处理，在图片上画一条线
        canvas.drawLine(30,32,25,21,paint);

        //显示图片
        ImageView imageView = findViewById(R.id.iv_image_copy);
        imageView.setImageBitmap(bmCopy);

    }


}
