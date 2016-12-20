package com.example.crxc.a360safe.main.phoneGuard;

import android.app.Activity;
import android.content.Intent;

import com.example.crxc.a360safe.BasePresenter;
import com.example.crxc.a360safe.BaseView;

/**
 * Created by crxc on 2016/12/9.
 */

public interface PhoneGuardCompleteContract {
    interface View extends BaseView<Presenter>{


        void setSafePhoneNum(String safePhoneNum);

        void setDefendState(boolean defendState);

        Activity getActivity();
    }
    interface Presenter extends BasePresenter{

        void reset();
    }
}
