package com.example.crxc.a360safe.main.phoneGuard;

import android.app.Activity;
import android.content.Intent;

import com.example.crxc.a360safe.BaseView;

/**
 * Created by crxc on 2016/12/9.
 */

public interface PhoneGuardFourthlyContract {
    interface View extends BaseView<Presenter>{

        Activity getActivity();

        void setActivate(boolean adminActive);

        void startActivityForResult(Intent intent, int rqDeviceManageCode);
    }
    interface Presenter{

        void setActivate(boolean activated);

        void init();

        boolean getActivateState();
    }
}
