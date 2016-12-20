package com.example.crxc.a360safe.main.phoneGuard;

import android.app.Activity;
import android.content.Intent;

import com.example.crxc.a360safe.data.DataRepository;

/**
 * Created by crxc on 2016/12/17.
 */

public class PhoneGuardFifthPresenter implements PhoneGuardFifthContract.Presenter {

    private final PhoneGuardFifthContract.View mPhoneGuardFifthView;
    private final DataRepository mDataRepository;

    public PhoneGuardFifthPresenter(PhoneGuardFifthContract.View view, DataRepository dataRepository) {
        mPhoneGuardFifthView = view;
        mDataRepository = dataRepository;
        view.setPresenter(this);
    }

    @Override
    public void setDefendState(boolean isChecked) {
        mDataRepository.saveDefendState(isChecked);
    }

    @Override
    public void init() {
        boolean isChecked=mDataRepository.getDefendState();
        mPhoneGuardFifthView.setChecked(isChecked);
    }

    @Override
    public void openPhoneGuardCompleteActivity() {
        Activity activity=mPhoneGuardFifthView.getActivity();
        Intent intent = new Intent(activity, PhoneGuardCompleteActivity_.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
