package com.example.crxc.a360safe.version;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;

import com.example.crxc.a360safe.App;

import org.androidannotations.annotations.EReceiver;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.ReceiverAction;

import java.io.FileNotFoundException;


public class VersionUpdateReceiver extends BroadcastReceiver {
    long mId;

    public VersionUpdateReceiver() {
    }

    public VersionUpdateReceiver(long id) {
        mId = id;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        UpdateApk(intent,context);
    }

//    @ReceiverAction(actions = DownloadManager.ACTION_DOWNLOAD_COMPLETE)
    void UpdateApk(Intent intent, final Context context){
        long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
        if(id==mId){
            DownloadManager downloadManager= (DownloadManager) App.getApp().getSystemService(Context.DOWNLOAD_SERVICE);
//                downloadManager.openDownloadedFile(id);
                final Intent install=new Intent(Intent.ACTION_VIEW);
                Uri downloadedFile = downloadManager.getUriForDownloadedFile(id);
                install.setDataAndType(downloadedFile,"application/vnd.android.package-archive");
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startActivity(install);
                }
            },2000);
            context.unregisterReceiver(this);
        }

    }
}
