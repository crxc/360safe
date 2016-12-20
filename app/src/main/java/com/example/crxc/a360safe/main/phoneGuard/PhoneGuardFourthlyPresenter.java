package com.example.crxc.a360safe.main.phoneGuard;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.example.crxc.a360safe.data.DataRepository;
import com.example.crxc.a360safe.main.Constant;

/**
 * Created by crxc on 2016/12/17.
 */

public class PhoneGuardFourthlyPresenter implements PhoneGuardFourthlyContract.Presenter {

    private final PhoneGuardFourthlyContract.View mPhoneGuardFourthlyView;
    private final DataRepository mDataRepository;
    private ComponentName mAdmin;
    private DevicePolicyManager mDevicePolicyManager;
    private Activity activity;

    public PhoneGuardFourthlyPresenter(PhoneGuardFourthlyContract.View view, DataRepository dataRepository) {
        mPhoneGuardFourthlyView = view;
        mDataRepository = dataRepository;
        view.setPresenter(this);
    }

    @Override
    public void setActivate(boolean activated) {
        if(activated){
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdmin);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "大家好才是真的好，广州好迪");
            mPhoneGuardFourthlyView.startActivityForResult(intent, Constant.rqDeviceManageCode);
        }else {
            mDevicePolicyManager.removeActiveAdmin(mAdmin);
            mPhoneGuardFourthlyView.setActivate(false);
        }
    }

    @Override
    public void init() {
        activity=mPhoneGuardFourthlyView.getActivity();
        mDevicePolicyManager = (DevicePolicyManager) activity.getSystemService(Context.DEVICE_POLICY_SERVICE);
        mAdmin = new ComponentName(activity,MobileSafeAdminReceiver.class);
        boolean adminActive = isAdminActive();
        mPhoneGuardFourthlyView.setActivate(adminActive);
    }

    private boolean isAdminActive() {
        return mDevicePolicyManager.isAdminActive(mAdmin);
    }

    @Override
    public boolean getActivateState() {
        return isAdminActive();
    }
}
