package com.example.crxc.a360safe.main.HarassIntercept;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.crxc.a360safe.BasePresenter;
import com.example.crxc.a360safe.BaseView;

/**
 * Created by crxc on 2016/12/9.
 */

public interface HarassInterceptContract {
    interface View extends BaseView<Presenter>{

        RecyclerView getRv();

        Bundle getArguments();

        void setTitle(String s);

        void startActivityForResult(Intent intent, int rqEditBlackList);

        void showProgressBar();

        void hideProgressBar();
    }
    interface Presenter extends BasePresenter{

        void addBlackList();

        void updateItem(Intent data);
    }
}
