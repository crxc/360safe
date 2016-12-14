package com.example.crxc.a360safe.main.phoneGuard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by crxc on 2016/12/12.
 */

class MyViewPagerAdapter extends FragmentPagerAdapter {
    MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PhoneGuardFirstFragment_();
            case 1:
                return new PhoneGuardSecondFragment_();
            case 2:
                return new PhoneGuardThirdlyFragment_();
            case 3:
                return new PhoneGuardFourthlyFragment_();
            case 4:
                return new PhoneGuardFifthFragment_();

            default:
                return new PhoneGuardFirstFragment_();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
