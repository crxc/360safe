package com.example.crxc.a360safe.data;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.example.crxc.a360safe.App;
import com.example.crxc.a360safe.bean.BlackInfo;
import com.example.crxc.a360safe.bean.BlackInfoDao;
import com.example.crxc.a360safe.main.Constant;
import com.example.crxc.a360safe.util.L;
import com.example.crxc.a360safe.util.Md5Utils;
import com.example.crxc.a360safe.util.PackageInfoUtil;
import com.example.crxc.a360safe.util.SPUtils;
import com.example.crxc.a360safe.version.AppVersionInfo;
import com.example.crxc.a360safe.version.VersionManager;

import org.greenrobot.greendao.query.LazyList;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by crxc on 2016/12/9.
 */

public class DataRepository implements DataSource {
    private static DataRepository dataRepository=new DataRepository();
    private AppVersionInfo versionInfo;
    private BlackInfoDao mBlackInfoDao;
    private List<BlackInfo> mBlackInfos;

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

    @Override
    public String getNumber() {
        TelephonyManager telephonyManager= (TelephonyManager) App.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("HardwareIds") String number = telephonyManager.getLine1Number();
        return number;
    }

    @Override
    public void savePhoneNumber(String phoneNumber) {
        SPUtils.put(App.getApp(),Constant.sPhoneNumber,phoneNumber);
        L.d(phoneNumber);
    }

    @Override
    public CharSequence getPhoneNumber() {
        return (CharSequence) SPUtils.get(App.getApp(),Constant.sPhoneNumber,"");
    }

    @Override
    public String getUserNumber(Intent data) {
        ContentResolver resolver = App.getApp().getContentResolver();
        Uri uri = data.getData();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if(cursor==null){
            Toast.makeText(App.getApp(), "没有查询到联系人", Toast.LENGTH_SHORT).show();
            return "";
        }
        cursor.moveToFirst();
//            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        Cursor phoneCursor=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                ,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contactId,null,null);
        String userNumber = null;
        if(phoneCursor==null){
            Toast.makeText(App.getApp(), "没有查询到电话", Toast.LENGTH_SHORT).show();
            return "";
        }
        while (phoneCursor.moveToNext()){
            userNumber=phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        cursor.close();
        phoneCursor.close();
        return userNumber;
    }

    @Override
    public void saveSafePhoneNum(String userNumber) {
        SPUtils.put(App.getApp(),Constant.sSafeNum,userNumber);
    }

    @Override
    public String getSafePhoneNum() {
        return (String) SPUtils.get(App.getApp(),Constant.sSafeNum,"");
    }

    @Override
    public void saveDefendState(boolean isChecked) {
        SPUtils.put(App.getApp(),Constant.sDefendState,isChecked);
    }

    @Override
    public boolean getDefendState() {
        return (boolean) SPUtils.get(App.getApp(),Constant.sDefendState,false);
    }

    public void saveBlackInfo(BlackInfo blackInfo) {
        initBlackInfoDao();
        mBlackInfoDao.insert(blackInfo);
    }

    private void initBlackInfoDao() {
        if(mBlackInfoDao==null)
        mBlackInfoDao=App.getDaoSession().getBlackInfoDao();
    }

    @Override
    public List<BlackInfo> getBlackInfos(int offset,int limit) {
            initBlackInfoDao();
            QueryBuilder<BlackInfo> builder = mBlackInfoDao.queryBuilder();
            mBlackInfos = builder.offset(offset).limit(limit).list();
        return mBlackInfos;
    }

    @Override
    public List<BlackInfo> getBlackInfos() {
        return mBlackInfos;
    }

    @Override
    public void updateBlackInfo(BlackInfo blackInfo) {
        mBlackInfoDao.update(blackInfo);
    }

    @Override
    public void delete(BlackInfo blackInfo) {
        mBlackInfoDao.delete(blackInfo);
    }

    @Override
    public void loadingData(int i) {
        initBlackInfoDao();
        QueryBuilder<BlackInfo> builder = mBlackInfoDao.queryBuilder();
        List<BlackInfo> infos = builder.limit(i).offset(mBlackInfos.size()).list();
        mBlackInfos.addAll(infos);
    }

    private void initVersionInfo() {
        if(versionInfo==null){
            VersionManager versionManager = VersionManager.getInstance();
            versionInfo = versionManager.getVersionInfo();
        }
    }

}
