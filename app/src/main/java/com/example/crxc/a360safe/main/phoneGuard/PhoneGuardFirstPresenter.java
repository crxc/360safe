package com.example.crxc.a360safe.main.phoneGuard;

import com.example.crxc.a360safe.data.DataSource;

/**
 * Created by crxc on 2016/12/17.
 */

public class PhoneGuardFirstPresenter implements PhoneGuardFirstContract.Presenter {
    private final PhoneGuardFirstContract.View mPhoneGuardFirstView;
    private final DataSource mDataRepository;

    public PhoneGuardFirstPresenter(PhoneGuardFirstContract.View phoneGuardFirstView, DataSource dataRepository) {
        mPhoneGuardFirstView = phoneGuardFirstView;
        mDataRepository = dataRepository;
        phoneGuardFirstView.setPresenter(this);
    }
}
