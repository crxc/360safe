package com.example.crxc.a360safe.setting;

import android.app.Fragment;
import android.view.View;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.view.SettingItemView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/11.
 */
@EFragment(R.layout.fragment_setting)
public class SettingFragment extends Fragment implements SettingContract.View {
    SettingContract.Presenter presenter;
    @ViewById(R.id.setting_item_check_update)
    SettingItemView check_update;

    @ViewById(R.id.setting_item_locale_show)
    SettingItemView locale_show;

    @ViewById(R.id.setting_item_locale_style)
    SettingItemView locale_style;

    @ViewById(R.id.setting_item_no_spam)
    SettingItemView no_spam;


    @AfterViews
    void afterView() {
        initItemState();
    }

    private void initItemState() {
        presenter.initItemState();
    }

    @Click(R.id.setting_item_check_update)
    void setCheckUpdate(View v) {
        presenter.setCheckUpdate(v);
    }

    @Click(R.id.setting_item_locale_show)
    void setLocale_show(View v) {
//        presenter.setLocale_show(v);
    }

    @Click(R.id.setting_item_locale_style)
    void setLocale_style(View v) {
//        presenter.setLocale_style(v);
    }

    @Click(R.id.setting_item_no_spam)
    void setNo_spam(View v) {
        presenter.setNo_spam(v);
    }

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setAutoUpdate(boolean autoUpdate) {
        check_update.setSwitchState(autoUpdate);
        check_update.updateSwitchState();
    }

//    @Override
//    public void setLocale_show(boolean locale_show) {
//        this.locale_show.setSwitchState(locale_show);
//        this.locale_show.updateSwitchState();
//    }
//
//    @Override
//    public void setLocale_style(boolean locale_style) {
//        this.locale_style.setSwitchState(locale_style);
//        this.locale_style.updateSwitchState();
//    }

    @Override
    public void setNo_spam(boolean no_spam) {
        this.no_spam.setSwitchState(no_spam);
        this.no_spam.updateSwitchState();
    }
}
