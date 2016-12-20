package com.example.crxc.a360safe.main.phoneGuard;

import android.content.Intent;
import android.provider.ContactsContract;

import com.example.crxc.a360safe.data.DataRepository;
import com.example.crxc.a360safe.main.Constant;

/**
 * Created by crxc on 2016/12/17.
 */

public class PhoneGuardThirdlyPresenter implements PhoneGuardThirdlyContract.Presenter {

    private final PhoneGuardThirdlyContract.View mPhoneGuardSecondView;
    private final DataRepository mDataRepository;

    public PhoneGuardThirdlyPresenter(PhoneGuardThirdlyContract.View view, DataRepository dataRepository) {
        mPhoneGuardSecondView = view;
        mDataRepository = dataRepository;
        view.setPresenter(this);
    }

    @Override
    public void selectContact() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mPhoneGuardSecondView.startActivityForResult(intent, Constant.rqContentCode);
    }

    @Override
    public void returnUserNumber(Intent data) {
        String userNumber=mDataRepository.getUserNumber(data);
        mPhoneGuardSecondView.setPhoneNumber(userNumber);
        mPhoneGuardSecondView.setCursorPosition(userNumber.length());
        mDataRepository.saveSafePhoneNum(userNumber);
    }

    @Override
    public void init() {
        String phoneNum=mDataRepository.getSafePhoneNum();
        mPhoneGuardSecondView.setPhoneNumber(phoneNum);
    }

    @Override
    public void saveSafeNum(String s) {
        mDataRepository.saveSafePhoneNum(s);
    }
}
