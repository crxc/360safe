package com.example.crxc.a360safe.main.phoneGuard;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.data.DataRepository;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by crxc on 2016/12/18.
 */
@EActivity(R.layout.activity_phone_guard_complete)
public class PhoneGuardCompleteActivity extends AppCompatActivity {
    private DataRepository mDataRepository;

    @AfterViews
    void afterViews(){
        mDataRepository=DataRepository.getInstance();
        Fragment completeFragment = getCompleteFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,completeFragment)
                .commit();
    }
    @NonNull
    private Fragment getCompleteFragment() {
        PhoneGuardCompleteFragment_ CompleteFragment = new PhoneGuardCompleteFragment_();
        PhoneGuardCompletePresenter CompletePresenter = new PhoneGuardCompletePresenter(CompleteFragment, mDataRepository);
        return CompleteFragment;
    }
}
