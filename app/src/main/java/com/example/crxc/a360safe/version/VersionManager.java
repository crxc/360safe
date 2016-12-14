package com.example.crxc.a360safe.version;


import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;

import com.example.crxc.a360safe.App;
import com.example.crxc.a360safe.util.CloseUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by crxc on 2016/12/10.
 */

public class VersionManager {
    private static final String TAG = "VersionManager";
    private static final VersionManager versionManager=new VersionManager();
    private  AppVersionInfo VersionInfo;
    private String baseUrl="http://10.0.2.2:8080/";
    private FileOutputStream fileOutputStream;
    private boolean requestFinish=false;
    public static VersionManager getInstance(){
        return versionManager;
    }
    public  AppVersionInfo getVersionInfo(){
        if(VersionInfo!=null){
            return VersionInfo;
        }
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.SECONDS).build();
        Retrofit retrofit=new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        VersionService versionService = retrofit.create(VersionService.class);
        versionService.getVersionInfo("AppVersionServlet")
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<AppVersionInfo>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: 获取版本信息成功");
                        this.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: 获取版本信息出错");
                    }

                    @Override
                    public void onNext(AppVersionInfo appVersionInfo) {
                        Log.d(TAG, "onNext: 正在获取版本信息"+appVersionInfo.getVersionname());
                        synchronized (versionManager){
                            VersionInfo=appVersionInfo;
                            requestFinish=true;
                            versionManager.notifyAll();
                        }
                    }
                });

        synchronized (versionManager){
            try {
                if(!requestFinish){
                    versionManager.wait(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return VersionInfo;
        }
    }
    public void updateApp(String url){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        VersionUpdateService updateService = retrofit.create(VersionUpdateService.class);
        updateService.downloadApk(url)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        String downLocation= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/1080.apk";
                        writeToDisk(responseBody,downLocation);
                    }
                });
    }

    private void writeToDisk(ResponseBody responseBody, String downLocation) {
        InputStream inputStream = responseBody.byteStream();
        FileOutputStream fileOutputStream = null;
        try {
             fileOutputStream =  new FileOutputStream(downLocation);
            byte[] bytes=new byte[8096];
            int len;
            while ((len=inputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,len);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally {
            CloseUtil.close(fileOutputStream);
            CloseUtil.close(inputStream);
        }
    }


}
