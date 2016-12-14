package com.example.crxc.a360safe.data;

import com.example.crxc.a360safe.version.AppVersionInfo;

import rx.Subscriber;

/**
 * Created by crxc on 2016/12/9.
 */

public interface DataSource {

    int getLastVersionCode();

    String getCurrentVersionName();

    int getCurrentVersionCode();

    String getLastVersionDownUrl();

    boolean getAutoUpdate();

    void setAutoUpdate(boolean switchState);

    void setNo_spam(boolean switchState);

//    void setLocale_style(boolean switchState);
//
//    void setLocale_show(boolean switchState);
//
//    boolean getLocale_show();
//
//    boolean getLocale_style();

    boolean getNo_spam();

    String getPwd();

    void savePwd(String pwd);

    void saveDownId(long id);
}
