package com.example.crxc.a360safe.main.phoneGuard;

import android.support.v4.app.Fragment;

import com.example.crxc.a360safe.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Created by crxc on 2016/12/12.
 */
@EFragment(R.layout.fragment_phone_guard_fourthly)
public class PhoneGuardFourthlyFragment extends PhoneGuardBaseFragment {
    @Click(R.id.pre)
    void pre(){
        super.pre();
    }
    @Click(R.id.next)
    void next(){
        super.next();
    }
}
