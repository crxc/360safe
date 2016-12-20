package com.example.crxc.a360safe.main.phoneGuard;

import android.app.Activity;

import com.example.crxc.a360safe.BaseView;

/**
 * Created by crxc on 2016/12/9.
 */

public interface PhoneGuardFifthContract {
    interface View extends BaseView<Presenter>{

        void setChecked(boolean isChecked);

        Activity getActivity();
    }
    interface Presenter{

        void setDefendState(boolean isChecked);

        void init();

        void openPhoneGuardCompleteActivity();

    }
}
