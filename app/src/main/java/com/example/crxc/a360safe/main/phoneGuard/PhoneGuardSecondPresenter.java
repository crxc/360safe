package com.example.crxc.a360safe.main.phoneGuard;

import android.text.TextUtils;

import com.example.crxc.a360safe.data.DataSource;

/**
 * Created by crxc on 2016/12/17.
 */

public class PhoneGuardSecondPresenter implements PhoneGuardSecondContract.Presenter {
    private final PhoneGuardSecondContract.View mPhoneGuardSecondView;
    private final DataSource mDataRepository;

    public PhoneGuardSecondPresenter(PhoneGuardSecondContract.View phoneGuardSecondView, DataSource dataRepository) {
        mPhoneGuardSecondView = phoneGuardSecondView;
        mDataRepository = dataRepository;
        mPhoneGuardSecondView.setPresenter(this);
    }
    @Override

    public void bindSimCard() {
        String phoneNumber=mDataRepository.getNumber();
        mDataRepository.savePhoneNumber(phoneNumber);

    }

    @Override
    public void unBindSimCard() {
        mDataRepository.savePhoneNumber("");
    }

    @Override
    public boolean isBindSim() {
        return !TextUtils.isEmpty(mDataRepository.getPhoneNumber());
    }
}
