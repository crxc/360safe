package com.example.crxc.a360safe.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.crxc.a360safe.data.DataSource;
import com.example.crxc.a360safe.main.HarassIntercept.HarassInterceptActivity_;
import com.example.crxc.a360safe.main.phoneGuard.PhoneGuardActivity_;
import com.example.crxc.a360safe.setting.SettingActivity_;
import com.example.crxc.a360safe.util.L;
import com.example.crxc.a360safe.util.Md5Utils;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by crxc on 2016/12/9.
 */
 class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;
    private final DataSource mData;
    private Activity activity;

    private static final String TAG = "MainPresenter";
    private boolean mAutoUpdate;
    private String mPwd;
    private boolean mNo_spam;
    private boolean mPreCompleted;

    MainPresenter(MainContract.View view, DataSource data) {
        this.mView = view;
        this.mData = data;
        view.setPresenter(this);
        preLoad();
    }

    public void preLoad() {
        Observable.create(new Observable.OnSubscribe<DataSource>() {
            @Override
            public void call(Subscriber<? super DataSource> subscriber) {
                subscriber.onNext(mData);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<DataSource>() {
                    @Override
                    public void onCompleted() {
                        mPreCompleted=true;
                        unsubscribe();
                        L.d(TAG,"预加载数据完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.d(TAG,"预加载数据异常"+e.getMessage());
                    }

                    @Override
                    public void onNext(DataSource dataSource) {
//                        设置数据
                        mAutoUpdate = mData.getAutoUpdate();
                        mNo_spam = mData.getNo_spam();
                        mPwd =mData.getPwd();
                    }
                });
    }

    @Override
    public void savePwd(String pwd) {
        mData.savePwd(pwd);
        preLoad();
    }

    @Override
    public boolean confirmPwd(String pwd) {
        return mData.getPwd().equals(Md5Utils.md5(pwd));
    }


    @Override
    public void startHeaderAnimation() {
        mView.startAnimation();
    }

    public void openSettingActivity() {
        if(mPreCompleted){
            Bundle bundle = new Bundle();
            bundle.putBoolean(Constant.bAutoUpdate,mAutoUpdate);
            bundle.putBoolean(Constant.bNoSpam,mNo_spam);
            goTo(SettingActivity_.class,bundle);
        }
    }

    @Override
    public View.OnClickListener getOnClickListener(int position) {
        return new MyGridLayoutClickListener(position);
    }

    private class MyGridLayoutClickListener implements View.OnClickListener {
        private int mPosition;

        MyGridLayoutClickListener(int mPosition) {
            this.mPosition = mPosition;
        }

        @Override
        public void onClick(View v) {
            switch (mPosition) {
                case 0:
                    if (TextUtils.isEmpty(mPwd)) {
//                        goTo(PhoneGuardActivity_.class);
                        mView.showPwdInitDialog();
                    } else {
                        mView.showPwdConfirmDialog();
                    }
                    break;
                case 1:
                    goTo(HarassInterceptActivity_.class);
                    break;
                default:
                    goTo(PhoneGuardActivity_.class);
            }
        }
    }
    private void goTo(Class<? extends Activity> Class, Bundle bundle) {
        activity = mView.getActivity();
        Intent intent = new Intent(activity, Class);
        intent.putExtras(bundle);
        mView.getActivity().startActivity(intent);
    }
    private void goTo(Class<? extends Activity> Class) {
        activity = mView.getActivity();
        Intent intent = new Intent(activity, Class);
        mView.getActivity().startActivity(intent);
    }
}
