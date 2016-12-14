package com.example.crxc.a360safe.main;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.crxc.a360safe.BaseView;

/**
 * Created by crxc on 2016/12/9.
 */

public interface MainContract {
    interface View extends BaseView<Presenter>{

        void goToSettingActivity();

        void startAnimation();

        Activity getActivity();

        void showPwdInitDialog();

        void showPwdConfirmDialog();
    }
    interface Presenter{
        void startHeaderAnimation();

        void openSettingActivity();

        android.view.View.OnClickListener getOnClickListener(int position);


        void preLoad();

        void savePwd(String pwd);

        boolean confirmPwd(String pwd);
    }
}
