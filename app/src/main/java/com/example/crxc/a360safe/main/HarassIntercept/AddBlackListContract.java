package com.example.crxc.a360safe.main.HarassIntercept;

import android.os.Bundle;

import com.example.crxc.a360safe.BasePresenter;
import com.example.crxc.a360safe.BaseView;

/**
 * Created by crxc on 2016/12/9.
 */

public interface AddBlackListContract {
    interface View extends BaseView<Presenter>{

        String getBlackNum();

        int getBlackType();

        Bundle getArguments();

        void setTitle(String title);

        void setPhone(String mPhone);

        void setType(int mType);

        void setPhoneEdtEnable(boolean b);
    }
    interface Presenter extends BasePresenter{

        void cancel();

        void save();
    }
}
