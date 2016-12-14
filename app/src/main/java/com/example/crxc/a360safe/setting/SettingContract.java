package com.example.crxc.a360safe.setting;

import com.example.crxc.a360safe.BaseView;

/**
 * Created by crxc on 2016/12/9.
 */

public interface SettingContract {
    interface View extends BaseView<Presenter>{

        void setAutoUpdate(boolean autoUpdate);
//
//        void setLocale_show(boolean locale_show);
//
//        void setLocale_style(boolean locale_style);

        void setNo_spam(boolean no_spam);
    }
    interface Presenter{

        void setCheckUpdate(android.view.View v);

        void initItemState();

        void setNo_spam(android.view.View v);
//
//        void setLocale_style(android.view.View v);
//
//        void setLocale_show(android.view.View v);
    }
}
