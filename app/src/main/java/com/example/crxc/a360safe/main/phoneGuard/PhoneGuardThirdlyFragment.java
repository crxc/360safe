package com.example.crxc.a360safe.main.phoneGuard;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.main.Constant;
import com.example.crxc.a360safe.main.MainActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/12.
 */
@EFragment(R.layout.fragment_phone_guard_thirdly)
public class PhoneGuardThirdlyFragment extends PhoneGuardBaseFragment implements PhoneGuardThirdlyContract.View{
    private PhoneGuardThirdlyContract.Presenter mPresenter;
    @ViewById(R.id.edt_phone)
    EditText mEditText_phone;
    @Click(R.id.pre)
    void pre(){
        super.pre();
    }
    @Click(R.id.next)
    void next(){
        super.next();
        mPresenter.saveSafeNum(mEditText_phone.getText().toString());
    }

    @AfterViews
    void afterViews(){
        mPresenter.init();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK)return;
        switch (requestCode){
            case Constant.rqContentCode:
                mPresenter.returnUserNumber(data);
                break;
        }
    }

    @Click(R.id.tv_select)
    void select(){
        mPresenter.selectContact();
    }

    @Override
    public void setPresenter(PhoneGuardThirdlyContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void setPhoneNumber(String userNumber) {
        mEditText_phone.setText(userNumber);
    }

    @Override
    public void setCursorPosition(int length) {
        mEditText_phone.setSelection(length);
    }
}
