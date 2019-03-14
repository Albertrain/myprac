package com.train.amm.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import com.train.amm.imp.MidMan;

public class MyService extends Service {
    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("unbind");
        return super.onUnbind(intent);

    }

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
     * #Service
     * * 就是默默运行在后台的组件，可以理解为是没有前台的activity，适合用来运行不需要前台界面的代码
     * * 服务可以被手动关闭，不会重启，但是如果被自动关闭，内存充足就会重启
     * * startService启动服务的生命周期
     * 	* onCreate-onStartCommand-onDestroy
     * * 重复的调用startService会导致onStartCommand被重复调用
     *
     * ---
     * # 进程优先级
     * 1. 前台进程：拥有前台activity（onResume方法被调用）
     * 2. 可见进程：拥有可见activity（onPause方法被调用）
     * 3. 服务进程：不到万不得已不会被回收，而且即便被回收，内存充足时也会被重启
     * 4. 后台进程：拥有后台activity（activity的onStop方法被调用了），很容易被回收
     * 5. 空进程：没有运行任何activity，很容易被回收
     *
     *
     *
     * #服务两种启动方式
     * * startService：服务被启动之后，跟启动它的组件没有一毛钱关系
     * * bindService：跟启动它的组件同生共死
     * * 绑定服务和解绑服务的生命周期方法：onCreate->onBind->onUnbind->onDestroy
     */


    @Override
    public IBinder onBind(Intent intent) {
        //服务的启动有显示和隐式
        //在清单文件中配置
        System.out.println("bind");
        return new MyBind();
    }

    public class MyBind extends Binder implements MidMan {
       public void mid(){
           serviceMethod();
       }

       public void mid1(){
           System.out.println("我是mid1,不要找我");
       }
    }
    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("service create");
    }

    /**
     * Called by the system every time a client explicitly starts the service by calling
     * {@link Context#startService}, providing the arguments it supplied and a
     * unique integer token representing the start request.  Do not call this method directly.
     *
     * <p>For backwards compatibility, the default implementation calls
     * {@link #onStart} and returns either {@link #START_STICKY}
     * or {@link #START_STICKY_COMPATIBILITY}.
     *
     * <p class="caution">Note that the system calls this on your
     * service's main thread.  A service's main thread is the same
     * thread where UI operations take place for Activities running in the
     * same process.  You should always avoid stalling the main
     * thread's event loop.  When doing long-running operations,
     * network calls, or heavy disk I/O, you should kick off a new
     * thread, or use {@link AsyncTask}.</p>
     *
     * @param intent  The Intent supplied to {@link Context#startService},
     *                as given.  This may be null if the service is being restarted after
     *                its process has gone away, and it had previously returned anything
     *                except {@link #START_STICKY_COMPATIBILITY}.
     * @param flags   Additional data about this start request.
     * @param startId A unique integer representing this specific request to
     *                start.  Use with {@link #stopSelfResult(int)}.
     * @return The return value indicates what semantics the system should
     * use for the service's current started state.  It may be one of the
     * constants associated with the {@link #START_CONTINUATION_MASK} bits.
     * @see #stopSelfResult(int)
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("service onstartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Called by the system to notify a Service that it is no longer used and is being removed.  The
     * service should clean up any resources it holds (threads, registered
     * receivers, etc) at this point.  Upon return, there will be no more calls
     * in to this Service object and it is effectively dead.  Do not call this method directly.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(" service destroy");
    }


    public void serviceMethod(){
        System.out.println("我是在服务里的方法");
    }
}
