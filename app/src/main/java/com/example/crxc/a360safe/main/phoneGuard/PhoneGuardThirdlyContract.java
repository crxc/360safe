package com.example.crxc.a360safe.main.phoneGuard;

import android.app.Activity;
import android.content.Intent;

import com.example.crxc.a360safe.BaseView;

/**
 * Created by crxc on 2016/12/9.
 */

public interface PhoneGuardThirdlyContract {
    interface View extends BaseView<Presenter>{

        void startActivityForResult(Intent intent, int rqContentCode);

        Activity getActivity();

        void setPhoneNumber(String userNumber);

        void setCursorPosition(int length);
    }
    interface Presenter{

        void selectContact();

        void returnUserNumber(Intent data);

        void init();

        void saveSafeNum(String s);
    }
}
