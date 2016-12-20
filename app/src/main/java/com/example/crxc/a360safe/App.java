package com.example.crxc.a360safe;

import android.app.Application;

import com.example.crxc.a360safe.bean.DaoMaster;
import com.example.crxc.a360safe.bean.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by crxc on 2016/12/8.
 */

public class App extends Application {
    private static App mApp;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp=this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "black_list_db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static App getApp() {
        return mApp;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
