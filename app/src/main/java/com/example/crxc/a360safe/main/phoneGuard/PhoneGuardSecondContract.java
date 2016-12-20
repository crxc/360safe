package com.example.crxc.a360safe.main.phoneGuard;

import com.example.crxc.a360safe.BaseView;

/**
 * Created by crxc on 2016/12/9.
 */

public interface PhoneGuardSecondContract {
    interface View extends BaseView<Presenter>{

    }
    interface Presenter{

        void bindSimCard();

        void unBindSimCard();

        boolean isBindSim();
    }
}
