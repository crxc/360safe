package com.example.crxc.a360safe;

import android.app.Application;

/**
 * Created by crxc on 2016/12/8.
 */

public class App extends Application {
    private static App mApp;
    public App() {
        mApp=this;
    }

    public static App getApp() {
        return mApp;
    }
}
