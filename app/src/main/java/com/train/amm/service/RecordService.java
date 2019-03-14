package com.train.amm.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.train.amm.utils.Utils;

public class RecordService extends Service {
    private MediaRecorder recorder;
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
     * #电话窃听器
     * * 电话状态：空闲、响铃、接听
     * * 获取电话管理器，设置侦听
     *
     * TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
     * 		tm.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        telephonyManager.listen(new MyListener(),PhoneStateListener.LISTEN_CALL_STATE);
    }

    class MyListener extends PhoneStateListener{
        /**
         * Callback invoked when device call state changes.
         *
         * @param state       call state
         * @param phoneNumber call phone number. If application does not have
         *                    {@link Manifest.permission#READ_CALL_LOG READ_CALL_LOG} permission or carrier
         *                    privileges (see {@link TelephonyManager#hasCarrierPrivileges}), an empty string will be
         *                    passed as an argument.
         * @see TelephonyManager#CALL_STATE_IDLE
         * @see TelephonyManager#CALL_STATE_RINGING
         * @see TelephonyManager#CALL_STATE_OFFHOOK
         */
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            //read phone state 要权限
            //录音需要写入外部存储权限和record audio权限
            super.onCallStateChanged(state, phoneNumber);

            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    System.out.println("idle");
                    Utils.showShortToast(getApplicationContext(),"idle");
                    //空闲时实放recorder资源
                    if(recorder != null){
                        recorder.stop();
                        recorder.release();
                        recorder = null;
                    }
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    System.out.println("ring");
                    Utils.showShortToast(getApplicationContext(),"ring");
                    if(recorder == null){
                        recorder = new MediaRecorder();
                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);  //录音来源
                        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //文件格式
                        recorder.setOutputFile("sdcard/luyin.3gp");   //存储位置
                        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);  //编码格式
                        try {
                            recorder.prepare();  //准备录音
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    System.out.println("offhook");
                    Utils.showShortToast(getApplicationContext(),"offhook");
                    //开始录音
                    if(recorder != null){
                        recorder.start();
                    }
                    break;
            }
        }
    }
}
