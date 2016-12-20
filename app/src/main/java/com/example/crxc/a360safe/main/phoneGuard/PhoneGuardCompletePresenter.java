package com.example.crxc.a360safe.main.phoneGuard;

import android.app.Activity;
import android.content.Intent;

import com.example.crxc.a360safe.data.DataRepository;
import com.example.crxc.a360safe.data.DataSource;

/**
 * Created by crxc on 2016/12/18.
 */
public class PhoneGuardCompletePresenter implements PhoneGuardCompleteContract.Presenter{

    private final PhoneGuardCompleteContract.View mView;
    private final DataSource mDataRepository;

    public PhoneGuardCompletePresenter(PhoneGuardCompleteContract.View view, DataRepository dataRepository) {
        mView=view;
        mDataRepository=dataRepository;
        mView.setPresenter(this);
    }

    @Override
    public void init() {
        String safePhoneNum = mDataRepository.getSafePhoneNum();
        mView.setSafePhoneNum(safePhoneNum);
        boolean defendState = mDataRepository.getDefendState();
        mView.setDefendState(defendState);
    }

    @Override
    public void reset() {
        Activity activity=mView.getActivity();
        Intent intent=new Intent(activity,PhoneGuardActivity_.class);
        activity.startActivity(intent);
    }
}
