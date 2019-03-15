package com.train.amm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class OpenClothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cloth);

        Bitmap bitmapSrc = BitmapFactory.decodeResource(getResources(),R.drawable.awaiyi);
        final Bitmap bitmapCopy = Bitmap.createBitmap(bitmapSrc.getWidth(),bitmapSrc.getHeight(),bitmapSrc.getConfig());

        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmapCopy);

        canvas.drawBitmap(bitmapSrc,new Matrix(),paint);

        final ImageView imageView = findViewById(R.id.iv_waiyi);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action){
                    case MotionEvent.ACTION_MOVE :
                        int x = (int) event.getX();
                        int y = (int) event.getY();

                        for(int i = -25; i <= 25; i++){
                            for(int j = -25; j <= 25; j++){
                                //把用户划过的坐标置为透明色
                                //改变指定的像素颜色
                                if(Math.sqrt(i*i + j*j) <= 25){
                                    if(x + i < bitmapCopy.getWidth() && y + j < bitmapCopy.getHeight() && x + i >= 0 && y + j >= 0){
                                        bitmapCopy.setPixel(x + i, y + j, Color.TRANSPARENT);
                                        imageView.setImageBitmap(bitmapCopy);
                                    }
                                }
                            }
                        }
                        break;
                }
                return true;
            }
        });

    }
}
