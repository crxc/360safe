package com.example.crxc.a360safe.splash;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Path;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.webkit.MimeTypeMap;

import com.example.crxc.a360safe.App;
import com.example.crxc.a360safe.data.DataSource;
import com.example.crxc.a360safe.version.VersionUpdateReceiver;
//import com.example.crxc.a360safe.version.VersionUpdateReceiver_;

import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by crxc on 2016/12/9.
 */

public class SplashPresenter implements SplashContract.Presenter {
    private final SplashContract.View mSplashView;
    private final DataSource mDataRepository;

    public SplashPresenter(@NotNull SplashContract.View mSplashView,@NotNull  DataSource mDataRepository) {
        this.mSplashView = mSplashView;
        this.mDataRepository = mDataRepository;
        mSplashView.setPresenter(this);
    }

    @Override
    public void getUpdate() {
        boolean autoUpdate = mDataRepository.getAutoUpdate();
        if(!autoUpdate){
            mSplashView.goToMainActivity();
            return;
        }
        int lastVersion=getLastVersionCode();
        int currentVersion= mDataRepository.getCurrentVersionCode();
        if(lastVersion<=currentVersion){
                    mSplashView.goToMainActivity();
        }else {
            mSplashView.showUpdateDialog();
        }
    }

    @Override
    public void updateFromInternet() {
        String url = mDataRepository.getLastVersionDownUrl();
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //调用系统下载服务
        DownloadManager downloadManage = (DownloadManager) App.getApp().getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
        request.setMimeType("application/vnd.android.package-archive");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"1080.apk");
        request.setAllowedOverMetered(true);
        request.setTitle("1080手机卫士");
        request.setDescription("人无远虑，必有近忧");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        long id = downloadManage.enqueue(request);
        mDataRepository.saveDownId(id);
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        Activity activity=mSplashView.getActivity();
        activity.registerReceiver(new VersionUpdateReceiver(id),filter);
    }

    @Override
    public void ShowVersion() {
        int currentVersionCode=mDataRepository.getCurrentVersionCode();
        String currentVersionName=mDataRepository.getCurrentVersionName();
        mSplashView.showVersion(currentVersionCode,currentVersionName);
    }

    private int getLastVersionCode() {
        return mDataRepository.getLastVersionCode();
    }
}
