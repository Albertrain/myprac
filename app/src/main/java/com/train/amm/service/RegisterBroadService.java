package com.train.amm.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.train.amm.broadcast.ScreenReceiver;

public class RegisterBroadService extends Service {

    private ScreenReceiver screenReceiver;

    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * {@link IBinder} is usually for a complex interface
     * that has been <a href="{@docRoot}guide/components/aidl.html">described using
     * aidl</a>.
     *
     * <p><em>Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process</em>.  More information about the main thread can be found in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
     * Threads</a>.</p>
     *
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return Return an IBinder through which clients can call on to the
     * service.
     *
     * 使用服务注册广播接收者
     * Android四大组件都要在清单文件中注册
     * 广播接收者比较特殊，既可以在清单文件中注册，也可以直接使用代码注册
     * 有的广播接收者，必须代码注册
     *
     * 电量改变
     * 屏幕锁屏和解锁
     * 注册广播接收者
     *
     * //创建广播接收者对象
     * receiver = new ScreenOnOffReceiver();
     * //通过IntentFilter对象指定广播接收者接收什么类型的广播
     * IntentFilter filter = new IntentFilter();
     * filter.addAction(Intent.ACTION_SCREEN_OFF);
     * filter.addAction(Intent.ACTION_SCREEN_ON);
     *
     * //注册广播接收者
     * registerReceiver(receiver, filter);
     * 解除注册广播接收者
     *
     * unregisterReceiver(receiver);
     * 解除注册之后，广播接收者将失去作用
     * 本地服务：服务和启动它的组件在同一个进程
     * 远程服务：服务和启动它的组件不在同一个进程
     * 远程服务只能隐式启动，类似隐式启动Activity，在清单文件中配置Service标签时，必须配置intent-filter子节点，并指定action子节点
     * AIDL
     * Android interface definition language
     * 安卓接口定义语言
     * 作用：跨进程通信
     * 应用场景：远程服务中的中间人对象，其他应用是拿不到的，那么在通过绑定服务获取中间人对象时，就无法强制转换，使用aidl，就可以在其他应用中拿到中间人类所实现的接口
     * 支付宝远程服务
     * 定义支付宝的服务，在服务中定义pay方法
     * 定义中间人对象，把pay方法抽取成接口
     * 把抽取出来的接口后缀名改成aidl
     * 中间人对象直接继承Stub对象
     * 注册这个支付宝服务，定义它的intent-Filter
     * 需要支付的应用
     * 把刚才定义好的aidl文件拷贝过来，注意aidl文件所在的包名必须跟原包名一致
     * 远程绑定支付宝的服务，通过onServiceConnected方法我们可以拿到中间人对象
     * 把中间人对象通过Stub.asInterface方法强转成定义了pay方法的接口
     * 调用中间人的pay方法
     * 五种前台进程
     * activity执行了onresume方法，获得焦点
     * 拥有一个跟正在与用户交互的activity绑定的服务
     * 拥有一个服务执行了startForeground()方法
     * 拥有一个正在执行onCreate()、onStart()或者onDestroy()方法中的任意一个的服务
     * 拥有一个正在执行onReceive方法的广播接收者
     * 两种可见进程
     * activity执行了onPause方法，失去焦点，但是可见
     * 拥有一个跟可见或前台activity绑定的服务
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //创建广播注册

        //1创建广播接收者对象
        screenReceiver = new ScreenReceiver();

        //2创建intent-filter对象
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

        //3注册广播接收者
        registerReceiver(screenReceiver,intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁广播注册
        unregisterReceiver(screenReceiver);
    }
}
