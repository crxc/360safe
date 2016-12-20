package com.example.crxc.a360safe.main.HarassIntercept;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.main.Constant;
import com.example.crxc.a360safe.util.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import static com.example.crxc.a360safe.R.id.rv;
import static junit.runner.Version.id;

/**
 * Created by crxc on 2016/12/19.
 */
@EFragment(R.layout.fragment_add_black_list)
public class AddBlackListFragment extends Fragment implements AddBlackListContract.View {

    private AddBlackListContract.Presenter mPresenter;
    @ViewById(R.id.rg)
    RadioGroup mRadioGroup;
    @ViewById(R.id.edt_phone)
    EditText mEditText;
    @ViewById(R.id.tv_title)
    TextView tv_title;

    @AfterViews
    void afterViews() {
        mPresenter.init();
    }

    @Click(R.id.btn_cancel)
    void cancel() {
        mPresenter.cancel();
    }

    @Click(R.id.btn_save)
    void save() {
        mPresenter.save();
    }

    @Override
    public void setPresenter(AddBlackListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public String getBlackNum() {
        return mEditText.getText().toString();
    }

    @Override
    public int getBlackType() {
        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_phone:
                return Constant.rbPhone;
            case R.id.rb_sms:
                return Constant.rbSms;
            case R.id.rb_all:
                return Constant.rbSmsAndPhone;
        }
        return 0;
    }

    @Override
    public void setTitle(String title) {
        tv_title.setText(title);
    }

    @Override
    public void setPhone(String mPhone) {
        mEditText.setText(mPhone);
    }

    @Override
    public void setType(int mType) {
        switch (mType) {
            case Constant.rbPhone:
                mRadioGroup.check(R.id.rb_phone);
                break;
            case Constant.rbSms:
                mRadioGroup.check(R.id.rb_sms);
                break;
            case Constant.rbSmsAndPhone:
                mRadioGroup.check(R.id.rb_all);
        }
    }

    @Override
    public void setPhoneEdtEnable(boolean b) {
        mEditText.setEnabled(false);
    }


}
