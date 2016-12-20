package com.example.crxc.a360safe.data;

import android.content.Intent;

import com.example.crxc.a360safe.bean.BlackInfo;
import com.example.crxc.a360safe.version.AppVersionInfo;

import java.util.List;

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

    String getNumber();

    void savePhoneNumber(String phoneNumber);

    CharSequence getPhoneNumber();

    String getUserNumber(Intent data);

    void saveSafePhoneNum(String userNumber);


    String getSafePhoneNum();

    void saveDefendState(boolean isChecked);

    boolean getDefendState();

    void saveBlackInfo(BlackInfo blackInfo);

    List<BlackInfo> getBlackInfos(int offset,int limit);

    List<BlackInfo> getBlackInfos();

    void updateBlackInfo(BlackInfo blackInfo);

    void delete(BlackInfo blackInfo);

    void loadingData(int i);
}
