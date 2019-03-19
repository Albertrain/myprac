package com.train.amm;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.train.amm.utils.Utils;

/**
 *
 */
public class ObjectAnimationActivity extends AppCompatActivity {

    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Utils.showShortToast(ObjectAnimationActivity.this,"点到");
            }
        });
    }

    public void translate(View v){
//		TranslateAnimation ta = new TranslateAnimation(0, 150, 0, 0);
//		ta.setDuration(2000);
//		ta.setFillAfter(true);
//		iv.startAnimation(ta);

        //target:动画作用于哪个组件
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationX", 10, 70, 20, 100);
        oa.setDuration(2000);
        oa.setRepeatCount(1);
        oa.setRepeatMode(ValueAnimator.REVERSE);
        oa.start();
    }

    public void scale(View v){
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "scaleX", 1, 1.6f, 1.2f, 2);
        oa.setDuration(2000);
        oa.start();
    }

    public void alpha(View v){
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "alpha", 0, 0.6f, 0.2f, 1);
        oa.setDuration(2000);
        oa.start();
    }

    public void rotate(View v){
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "rotationY", 0, 180, 90, 360);
        oa.setDuration(2000);
        oa.setRepeatCount(1);
        oa.setRepeatMode(ValueAnimator.REVERSE);
        oa.start();
    }

    public void fly(View v){
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator oa1 = ObjectAnimator.ofFloat(iv, "translationX", 10, 70, 20, 100);
        oa1.setDuration(2000);
        oa1.setRepeatCount(1);
        oa1.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator oa2 = ObjectAnimator.ofFloat(iv, "translationY", 10, 70, 20, 100);
        oa2.setDuration(2000);
        oa2.setRepeatCount(1);
        oa2.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator oa3 = ObjectAnimator.ofFloat(iv, "scaleX", 1, 1.6f, 1.2f, 2);
        oa3.setDuration(2000);
        oa3.setRepeatCount(1);
        oa3.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator oa4 = ObjectAnimator.ofFloat(iv, "rotation", 0, 180, 90, 360);
        oa4.setDuration(2000);
        oa4.setRepeatCount(1);
        oa4.setRepeatMode(ValueAnimator.REVERSE);

        //设置挨个飞
//		set.playSequentially(oa1, oa2, oa3, oa4);
        //设置一起飞
        set.playTogether(oa1, oa2, oa3, oa4);
        set.start();
    }

    public void xml(View v){
        Animator at = AnimatorInflater.loadAnimator(this, R.animator.objanimator);
        //设置作用于哪个组件
        at.setTarget(iv);
        at.start();
    }
}
