package com.example.crxc.a360safe.main.phoneGuard;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.crxc.a360safe.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/11.
 */
@EActivity(R.layout.activity_phone_guard)
public class PhoneGuardActivity extends AppCompatActivity {
    static MyViewPager sPager;
    @ViewById
    MyViewPager pager;
    @AfterViews
    void afterViews(){
        initViewPager();
    }

    private void initViewPager() {
        pager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        sPager=pager;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sPager=null;
    }
}
