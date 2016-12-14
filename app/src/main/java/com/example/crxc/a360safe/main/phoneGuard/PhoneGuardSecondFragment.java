package com.example.crxc.a360safe.main.phoneGuard;

import android.support.v4.app.Fragment;
import android.view.View;

import com.example.crxc.a360safe.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import static com.example.crxc.a360safe.main.phoneGuard.PhoneGuardActivity.sPager;

/**
 * Created by crxc on 2016/12/12.
 */
@EFragment(R.layout.fragment_phone_guard_second)
public class PhoneGuardSecondFragment extends PhoneGuardBaseFragment {
    @Click(R.id.pre)
    void pre(){
        super.pre();
    }
    @Click(R.id.next)
    void next(){
        super.next();
    }

    @Click(R.id.tv_bind)
    void bindChange(View view){
        view.setSelected(!view.isSelected());
    }
}
