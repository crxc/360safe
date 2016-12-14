package com.example.crxc.a360safe.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.crxc.a360safe.App;

/**
 * Created by crxc on 2016/12/8.
 */

public class PackageInfoUtil {
    static {
        try {
            packageInfo = App.getApp().getPackageManager().getPackageInfo(App.getApp().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static PackageInfo packageInfo;

    public static String getVersion(){
        return packageInfo.versionName;
    }
    public static int getVersionCode(){
        return packageInfo.versionCode;
    }
}
