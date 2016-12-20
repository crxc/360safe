package com.example.crxc.a360safe;

import android.app.Activity;

/**
 * Created by crxc on 2016/12/9.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    Activity getActivity();
}
