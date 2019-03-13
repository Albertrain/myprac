package com.train.amm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity的四种启动模式
 * 每个应用会有一个Activity任务栈，存放已启动的Activity
 *
 * Activity的启动模式，修改任务栈的排列情况
 * standard 标准启动模式
 * singleTop 单一顶部模式
 * 如果任务栈的栈顶存在这个要开启的activity，不会重新的创建activity，而是复用已经存在的activity。保证栈顶如果存在，不会重复创建。
 * 应用场景：浏览器的书签
 * singeTask 单一任务栈，在当前任务栈里面只能有一个实例存在
 *
 * 当开启activity的时候，就去检查在任务栈里面是否有实例已经存在，如果有实例存在就复用这个已经存在的activity，并且把这个activity上面的所有的别的activity都清空，复用这个已经存在的activity。保证整个任务栈里面只有一个实例存在
 * 应用场景：浏览器的activity
 * 如果一个activity的创建需要占用大量的系统资源（cpu，内存）一般配置这个activity为singletask的启动模式。webkit内核 c代码
 * singleInstance启动模式非常特殊， activity会运行在自己的任务栈里面，并且这个任务栈里面只有一个实例存在
 *
 * 如果你要保证一个activity在整个手机操作系统里面只有一个实例存在，使用singleInstance
 * 应用场景： 电话拨打界面
 *
 * ##横竖屏切换的生命周期
 * >默认情况下 ，横竖屏切换， 销毁当前的activity，重新创建一个新的activity
 * >
 * > 快捷键ctrl+F11
 *
 * 在一些特殊的应用程序常见下，比如游戏，不希望横竖屏切换activity被销毁重新创建
 * 需求：禁用掉横竖屏切换的生命周期
 * 1. 横竖屏写死
 * 		android:screenOrientation="landscape"
 * 		android:screenOrientation="portrait"
 *
 * 2. 让系统的环境 不再去敏感横竖屏的切换。
 *
 * 		 android:configChanges="orientation|screenSize|keyboardHidden"
 *
 * ---
 * #掌握开启activity获取返回值
 * ###从A界面打开B界面， B界面关闭的时候，返回一个数据给A界面
 * 步骤：
 * 1. 开启activity并且获取返回值
 *
 * 		startActivityForResult(intent, 0);
 * 2. 在新开启的界面里面实现设置数据的逻辑
 *
 * 		Intent data = new Intent();
 * 		data.putExtra("phone", phone);
 * 		//设置一个结果数据，数据会返回给调用者
 * 		setResult(0, data);
 * 		finish();//关闭掉当前的activity，才会返回数据
 *
 * 3. 在开启者activity里面实现方法
 * 		onActivityResult(int requestCode, int resultCode, Intent data)
 * 		通过data获取返回的数据
 * 4. 根据请求码和结果码确定业务逻辑
 */
public class LunchModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_mode);
    }
}
