package com.train.amm;

import java.io.File;


import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;


public class XutilDownloadActivity extends Activity {

    private TextView tv_failure;
    private TextView tv_progress;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutil_download);

        tv_failure = (TextView) findViewById(R.id.tv_failure);
        tv_progress = (TextView) findViewById(R.id.tv_progress);
        pb = (ProgressBar) findViewById(R.id.pb);
    }

    public void xutilDownload(View v){

        HttpUtils utils = new HttpUtils();
        String fileName = "test.exe";
        //确定下载地址
        String path = "http://192.168.6.238:8080/" + fileName;
        utils.download(path, //下载地址
                "sdcard/test.exe", //文件保存路径
                true,//是否支持断点续传
                true, new RequestCallBack<File>() {

                    //下载成功后调用
                    @Override
                    public void onSuccess(ResponseInfo<File> arg0) {
                        Toast.makeText(XutilDownloadActivity.this, arg0.result.getPath(), Toast.LENGTH_SHORT).show();

                    }

                    //下载失败调用
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        tv_failure.setText(arg1);
                    }

                    @Override
                    public void onLoading(long total, long current,
                                          boolean isUploading) {
                        super.onLoading(total, current, isUploading);

                        pb.setMax((int)total);
                        pb.setProgress((int)current);
                        tv_progress.setText(current * 100 / total + "%");
                    }
                });
    }

}
