package com.example.crxc.a360safe.splash;

import android.app.Activity;

import com.example.crxc.a360safe.BaseView;

/**
 * Created by crxc on 2016/12/9.
 */

public interface SplashContract {
    interface View extends BaseView<Presenter>{
        void showUpdateDialog();
        void goToMainActivity();

        void showVersion(int currentVersionCode, String currentVersionName);

        Activity getActivity();
    }
    interface Presenter{
        void getUpdate();

        void updateFromInternet();

        void ShowVersion();
    }
}
