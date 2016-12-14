package com.example.crxc.a360safe.main.phoneGuard;

import android.support.v4.app.Fragment;
import android.view.View;

import static com.example.crxc.a360safe.main.phoneGuard.PhoneGuardActivity.sPager;

/**
 * Created by crxc on 2016/12/12.
 */

public abstract class PhoneGuardBaseFragment extends Fragment {
    void pre(){
        sPager.setCurrentItem(sPager.getCurrentItem()-1);
    }

    void next() {
        sPager.setCurrentItem(sPager.getCurrentItem()+1);
    }
}
