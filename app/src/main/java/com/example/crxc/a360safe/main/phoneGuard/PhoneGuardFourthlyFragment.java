package com.example.crxc.a360safe.main.phoneGuard;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.main.Constant;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by crxc on 2016/12/12.
 */
@EFragment(R.layout.fragment_phone_guard_fourthly)
public class PhoneGuardFourthlyFragment extends PhoneGuardBaseFragment implements PhoneGuardFourthlyContract.View{
    private PhoneGuardFourthlyContract.Presenter mPresenter;
    @ViewById(R.id.tv_activate)
    TextView mtvActivate;
    @Click(R.id.pre)
    void pre(){
        super.pre();
    }
    @Click(R.id.next)
    void next(){
        super.next();
    }
    @Click(R.id.tv_activate)
    void changeActivate(View view){
        boolean activated = !view.isActivated();
        mPresenter.setActivate(activated);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                    setActivate(mPresenter.getActivateState());
    }

    @AfterViews
    void afterViews(){
        mPresenter.init();
    }

    @Override
    public void setPresenter(PhoneGuardFourthlyContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void setActivate(boolean adminActive) {
        mtvActivate.setActivated(adminActive);
    }
}
