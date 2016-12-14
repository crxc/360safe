package com.example.crxc.a360safe.data;

import com.example.crxc.a360safe.App;
import com.example.crxc.a360safe.main.Constant;
import com.example.crxc.a360safe.util.L;
import com.example.crxc.a360safe.util.Md5Utils;
import com.example.crxc.a360safe.util.PackageInfoUtil;
import com.example.crxc.a360safe.util.SPUtils;
import com.example.crxc.a360safe.version.AppVersionInfo;
import com.example.crxc.a360safe.version.VersionManager;

/**
 * Created by crxc on 2016/12/9.
 */

public class DataRepository implements DataSource {
    private static DataRepository dataRepository=new DataRepository();
    private AppVersionInfo versionInfo;

    public static DataRepository getInstance(){
        return dataRepository;
    }

    @Override
    public String getCurrentVersionName() {
        return PackageInfoUtil.getVersion();
    }

    @Override
    public int getCurrentVersionCode() {
        return PackageInfoUtil.getVersionCode();
    }

    @Override
    public int getLastVersionCode() {
        initVersionInfo();
        return versionInfo==null?-1:versionInfo.getVersioncode();
    }

    @Override
    public String getLastVersionDownUrl() {
        initVersionInfo();
        return versionInfo==null?null:versionInfo.getUrl();
    }

    @Override
    public void setAutoUpdate(boolean switchState) {
        SPUtils.put(App.getApp(),Constant.bAutoUpdate,switchState);
    }

    @Override
    public void setNo_spam(boolean switchState) {
        SPUtils.put(App.getApp(),Constant.bNoSpam,switchState);
    }

//    @Override
//    public void setLocale_style(boolean switchState) {
//        SPUtils.put(App.getApp(),"Locale_style",switchState);
//    }
//
//    @Override
//    public void setLocale_show(boolean switchState) {
//        SPUtils.put(App.getApp(),"Locale_show",switchState);
//    }

    @Override
    public boolean getAutoUpdate() {
        return (boolean) SPUtils.get(App.getApp(),Constant.bAutoUpdate,false);
    }
//
//    @Override
//    public boolean getLocale_show() {
//        return (boolean) SPUtils.get(App.getApp(),"Locale_show",true);
//    }
//
//    @Override
//    public boolean getLocale_style() {
//        return (boolean) SPUtils.get(App.getApp(),"Locale_style",false);
//    }

    @Override
    public boolean getNo_spam() {
        return (boolean) SPUtils.get(App.getApp(),Constant.bNoSpam,false);
    }

    @Override
    public String getPwd() {
        return (String) SPUtils.get(App.getApp(), Constant.bPwd,"");
    }

    @Override
    public void savePwd(String pwd) {
        String md5 = Md5Utils.md5(pwd);
        SPUtils.put(App.getApp(),Constant.bPwd,md5);
    }

    @Override
    public void saveDownId(long id) {
        SPUtils.put(App.getApp(),Constant.sDownId,id);
    }

    private void initVersionInfo() {
        if(versionInfo==null){
            VersionManager versionManager = VersionManager.getInstance();
            versionInfo = versionManager.getVersionInfo();
        }
    }
}
