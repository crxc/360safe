package com.example.crxc.a360safe.setting;

import android.os.Bundle;
import android.view.View;

import com.example.crxc.a360safe.main.Constant;
import com.example.crxc.a360safe.view.SettingItemView;
import com.example.crxc.a360safe.data.DataSource;

import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by crxc on 2016/12/9.
 */

public class SettingPresenter implements SettingContract.Presenter {
    private final SettingContract.View mSettingView;
    private final DataSource mDataRepository;
    private final Bundle mBundle;

    public SettingPresenter(@NotNull SettingContract.View mSettingView, @NotNull  DataSource mDataRepository, Bundle bundle) {
        this.mSettingView = mSettingView;
        this.mDataRepository = mDataRepository;
        mSettingView.setPresenter(this);
        mBundle=bundle;
    }


    @Override
    public void initItemState() {
        initAutoUpdate();
        initNo_spam();
//        initLocale_style();
//        initLocale_show();
    }

    private void initAutoUpdate() {
        boolean autoUpdate = mBundle.getBoolean(Constant.bAutoUpdate);
        mSettingView.setAutoUpdate(autoUpdate);
    }

//    private void initLocale_show() {
//        boolean Locale_show = mDataRepository.getLocale_show();
//        mSettingView.setLocale_show(Locale_show);
//    }

//    private void initLocale_style() {
//        boolean Locale_style = mDataRepository.getLocale_style();
//        mSettingView.setLocale_style(Locale_style);
//    }

    private void initNo_spam() {
        boolean No_spam = mBundle.getBoolean(Constant.bNoSpam);
        mSettingView.setNo_spam(No_spam);
    }

    @Override
    public void setCheckUpdate(View v) {
        boolean switchState = setSwitchState((SettingItemView) v);
        mDataRepository.setAutoUpdate(switchState);
    }

    @Override
    public void setNo_spam(View v) {
        boolean switchState = setSwitchState((SettingItemView) v);
        mDataRepository.setNo_spam(switchState);
    }

//    @Override
//    public void setLocale_style(View v) {
//        boolean switchState = setSwitchState((SettingItemView) v);
//        mDataRepository.setLocale_style(switchState);
//    }
//
//    @Override
//    public void setLocale_show(View v) {
//        boolean switchState = setSwitchState((SettingItemView) v);
//        mDataRepository.setLocale_show(switchState);
//    }

    private boolean setSwitchState(SettingItemView v) {
        boolean switchState = !v.isSwitchState();
        v.setSwitchState(switchState);
        v.updateSwitchState();
        return switchState;
    }


}
