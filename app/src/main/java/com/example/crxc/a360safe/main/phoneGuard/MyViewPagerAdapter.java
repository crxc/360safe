package com.example.crxc.a360safe.main.phoneGuard;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.crxc.a360safe.data.DataRepository;

/**
 * Created by crxc on 2016/12/12.
 */

class MyViewPagerAdapter extends FragmentPagerAdapter {

    private DataRepository mDataRepository;

    MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mDataRepository = DataRepository.getInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return getFirstFragment();
            case 1:
                return getSecondFragment();
            case 2:
                return getThirdlyFragment();
            case 3:
                return getFourthlyFragment();
            case 4:
                return getFifthFragment();

            default:
                return getFirstFragment();
        }
    }

    @NonNull
    private Fragment getFirstFragment() {

        PhoneGuardFirstFragment_ firstFragment = new PhoneGuardFirstFragment_();
        PhoneGuardFirstPresenter firstPresenter = new PhoneGuardFirstPresenter(firstFragment, mDataRepository);
        return firstFragment;
    }

    @NonNull
    private Fragment getSecondFragment() {
        PhoneGuardSecondFragment_ secondFragment = new PhoneGuardSecondFragment_();
        PhoneGuardSecondPresenter secondPresenter = new PhoneGuardSecondPresenter(secondFragment, mDataRepository);
        return secondFragment;
    }

    @NonNull
    private Fragment getThirdlyFragment() {
        PhoneGuardThirdlyFragment_ thirdlyFragment = new PhoneGuardThirdlyFragment_();
        PhoneGuardThirdlyPresenter thirdlyPresenter = new PhoneGuardThirdlyPresenter(thirdlyFragment, mDataRepository);
        return thirdlyFragment;
    }

    @NonNull
    private Fragment getFourthlyFragment() {
        PhoneGuardFourthlyFragment_ fourthlyFragment = new PhoneGuardFourthlyFragment_();
        PhoneGuardFourthlyPresenter fourthlyPresenter = new PhoneGuardFourthlyPresenter(fourthlyFragment, mDataRepository);
        return fourthlyFragment;
    }

    @NonNull
    private Fragment getFifthFragment() {
        PhoneGuardFifthFragment_ fifthFragment = new PhoneGuardFifthFragment_();
        PhoneGuardFifthPresenter fifthPresenter = new PhoneGuardFifthPresenter(fifthFragment, mDataRepository);
        return fifthFragment;
    }




    @Override
    public int getCount() {
        return 5;
    }
}
