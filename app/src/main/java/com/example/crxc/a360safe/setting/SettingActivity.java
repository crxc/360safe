package com.example.crxc.a360safe.setting;

import android.support.v7.app.AppCompatActivity;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.data.DataRepository;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by crxc on 2016/12/9.
 */
@EActivity(R.layout.activity_setting)
public class SettingActivity extends AppCompatActivity {
    @AfterViews
    void afterViews(){
        initFragment();
    }

    private void initFragment() {
        SettingFragment_ settingFragment = new SettingFragment_();
        getFragmentManager().beginTransaction()
                .replace(R.id.frame,settingFragment)
                .commit();
        new SettingPresenter(settingFragment, DataRepository.getInstance(),getIntent().getExtras());
    }
}
