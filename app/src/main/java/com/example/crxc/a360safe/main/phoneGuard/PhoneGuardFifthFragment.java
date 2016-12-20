package com.example.crxc.a360safe.main.phoneGuard;

import android.support.v4.app.Fragment;
import android.widget.CheckBox;

import com.example.crxc.a360safe.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/12.
 */
@EFragment(R.layout.fragment_phone_guard_fifth)
public class PhoneGuardFifthFragment extends PhoneGuardBaseFragment implements PhoneGuardFifthContract.View{
    private PhoneGuardFifthContract.Presenter mPresenter;
    @ViewById(R.id.cb)
    CheckBox mCheckBox;
    @Click(R.id.pre)
    void pre(){
        super.pre();
    }
    @Click(R.id.next)
    void next(){
        mPresenter.openPhoneGuardCompleteActivity();
    }
    @CheckedChange(R.id.cb)
    void checkedChange(boolean isChecked){
        mPresenter.setDefendState(isChecked);
    }
    @AfterViews
    void afterViews(){
        mPresenter.init();
    }
    @Override
    public void setPresenter(PhoneGuardFifthContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void setChecked(boolean isChecked) {
        mCheckBox.setChecked(isChecked);
    }
}
