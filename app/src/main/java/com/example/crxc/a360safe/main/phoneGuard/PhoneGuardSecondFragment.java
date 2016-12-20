package com.example.crxc.a360safe.main.phoneGuard;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.example.crxc.a360safe.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import static com.example.crxc.a360safe.main.phoneGuard.PhoneGuardActivity.sPager;

/**
 * Created by crxc on 2016/12/12.
 */
@EFragment(R.layout.fragment_phone_guard_second)
public class PhoneGuardSecondFragment extends PhoneGuardBaseFragment implements PhoneGuardSecondContract.View{

    private PhoneGuardSecondContract.Presenter mPresenter;
    @ViewById(R.id.tv_bind)
    TextView tv_bind;
    @AfterViews
    void afterViews(){
        boolean isBindSim=mPresenter.isBindSim();
        tv_bind.setSelected(isBindSim);
    }

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
        if(view.isSelected()){
            mPresenter.bindSimCard();
        }else {
            mPresenter.unBindSimCard();
        }
    }

    @Override
    public void setPresenter(PhoneGuardSecondContract.Presenter presenter) {
        mPresenter=presenter;
    }
}
