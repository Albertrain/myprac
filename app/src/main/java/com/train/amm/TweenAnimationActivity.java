package com.train.amm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/***
 * 帧动画FrameAnimation
 * 多张图片快速切换，形成动画效果
 * 帧动画使用xml定义
 * 补间动画
 * 组件由原始状态向终极状态转变时，为了让过渡更自然，而自动生成的动画
 */
public class TweenAnimationActivity extends AppCompatActivity {
    private ImageView iv;
    private RotateAnimation ra;
    private AlphaAnimation aa;
    private ScaleAnimation sa;
    private TranslateAnimation ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);
        iv = (ImageView) findViewById(R.id.iv);
    }

    public void translate(View v) {
        /**
         * 10：表示的x坐标起始位置
         *
         * iv的真实x + 10
         * 100：表示x坐标的结束位置
         *
         * iv的真实x + 100
         * 20:表示y坐标的起始位置
         *
         * iv的真实y + 20
         * 200:表示y坐标的结束位置
         *
         * iv的真实y + 200
         */
//		ta = new TranslateAnimation(10, 100, 20, 200);

        /**
         * Animation.RELATIVETOSELF, 1:x坐标的初始位置
         *
         * iv的真实x + 1 * iv宽
         * Animation.RELATIVETOSELF, 0.5f：y坐标的起始位置
         *
         * iv的真实y + 0.5 * iv高
         */
        ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 2,
                Animation.RELATIVE_TO_SELF, -0.5f, Animation.RELATIVE_TO_SELF, 1.5f);
        //设置播放时间
        ta.setDuration(2000);
        //设置重复次数
        ta.setRepeatCount(1);
        ta.setRepeatMode(Animation.REVERSE);
        iv.startAnimation(ta);
    }

    public void scale(View v) {
        /**
         * 0.5f：表示x坐标缩放的初始位置
         * 0.5 * iv宽
         * 2：表示x坐标缩放的结束位置
         * 2 * iv宽
         * iv.getWidth() / 2：表示缩放点的x坐标
         *
         * iv的真实x + iv.getWidth() / 2
         */
//		sa = new ScaleAnimation(fromX, toX, fromY, toY, iv.getWidth() / 2, iv.getHeight() / 2);

        /**
         * Animation.RELATIVETOSELF, 0.5f：表示缩放点的x坐标
         * iv的真实x + 0.5 * iv宽
         */
        sa = new ScaleAnimation(0.5f, 2, 0.1f, 3, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(2000);
        //填充动画的结束位置
        sa.setRepeatCount(1);
        sa.setRepeatMode(Animation.REVERSE);
        //填充动画的结束位置
        sa.setFillAfter(true);
        iv.startAnimation(sa);
    }

    public void alpha(View v) {
        //0表示动画的起始透明度
        //0.5f表示动画的结束透明度
        //透明度，0是完全透明，1是完全不透明
        aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);
        sa.setRepeatCount(1);
        iv.startAnimation(aa);
    }

    public void rotate(View v) {
        /**
         * 20表示动画开始时的iv的角度
         * 360表示动画结束时iv的角度
         * 默认旋转的圆心在iv左上角
         *
         * RotateAnimation ra = new RotateAnimation(20, 360);
         * 20,360的意义和上面一样
         * 指定圆心坐标，相对于自己，值传入0.5，那么圆心的x坐标：真实X + iv宽度 * 0.5
         * 圆心的Y坐标：真实Y + iv高度 * 0.5
         */
        ra = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);
        ra.setRepeatCount(1);
        ra.setRepeatMode(Animation.REVERSE);
        iv.startAnimation(ra);
    }

    public void fly(View v) {
        //创建动画集合
        AnimationSet set = new AnimationSet(false);
        //往集合中添加动画
        set.addAnimation(ta);
        set.addAnimation(sa);
        set.addAnimation(ra);
        set.addAnimation(aa);

        iv.startAnimation(set);
    }
}
