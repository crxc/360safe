package com.example.crxc.a360safe.main.phoneGuard;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.main.Constant;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/12.
 */
@EFragment(R.layout.fragment_phone_guard_complete)
public class PhoneGuardCompleteFragment extends Fragment implements PhoneGuardCompleteContract.View{
    private PhoneGuardCompleteContract.Presenter mPresenter;
    @ViewById(R.id.tv_defend)
    TextView mTvDefend;
    @ViewById(R.id.tv_phoneNum)
    TextView mTvPhoneNum;


    @AfterViews
    void afterViews(){
        mPresenter.init();
    }
    @Click(R.id.tv_reset)
    void reset(){
        mPresenter.reset();
    }

    @Override
    public void setPresenter(PhoneGuardCompleteContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void setSafePhoneNum(String safePhoneNum) {
        mTvPhoneNum.setText(safePhoneNum);
    }

    @Override
    public void setDefendState(boolean defendState) {
        mTvDefend.setSelected(defendState);
    }
}
