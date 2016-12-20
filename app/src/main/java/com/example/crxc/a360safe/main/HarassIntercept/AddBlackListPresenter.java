package com.example.crxc.a360safe.main.HarassIntercept;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.crxc.a360safe.bean.BlackInfo;
import com.example.crxc.a360safe.data.DataRepository;
import com.example.crxc.a360safe.main.Constant;

import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by crxc on 2016/12/19.
 */
public class AddBlackListPresenter implements AddBlackListContract.Presenter {
    AddBlackListContract.View mView;
    DataRepository mDataRepository;
    private List<BlackInfo> mBlackInfos;
    private boolean mIsChange=false;
    private int mPosition;

    public AddBlackListPresenter(AddBlackListContract.View view, DataRepository dataRepository) {
        mView = view;
        mDataRepository = dataRepository;
        mView.setPresenter(this);
    }

    @Override
    public void init() {
        Bundle bundle = mView.getArguments();
        mBlackInfos = mDataRepository.getBlackInfos();
        if (bundle != null) {
            mIsChange=true;
            mView.setTitle("更新黑名单");
            mPosition = bundle.getInt(Constant.bItemPosition);
            BlackInfo blackInfo = mBlackInfos.get(mPosition);
            mView.setPhone(blackInfo.getMPhone());
            mView.setType(blackInfo.getMType());
            mView.setPhoneEdtEnable(false);
        }
    }

    @Override
    public void cancel() {
        mView.getActivity().finish();
    }

    @Override
    public void save() {
        String num = mView.getBlackNum();
        int type = mView.getBlackType();
        BlackInfo blackInfo = new BlackInfo(num, type);
        if(mIsChange){
            BlackInfo info=mBlackInfos.get(mPosition);
            info.setMPhone(num);
            info.setMType(type);
            mDataRepository.updateBlackInfo(info);
            response(Constant.rcEditBlackList);
        }else {
            mDataRepository.saveBlackInfo(blackInfo);
            mBlackInfos.add(blackInfo);
            response(Constant.rcAddBlackList);
        }
    }

    private void response(int responseCode) {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putInt(Constant.bItemPosition,mPosition);
        intent.putExtras(bundle);
        Activity activity=mView.getActivity();
        activity.setResult(responseCode,intent);
        activity.finish();
    }
}
