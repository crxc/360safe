package com.example.crxc.a360safe.main.phoneGuard;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

/**
 * Created by crxc on 2016/12/17.
 */

/**
 * 可控制能否左滑
 */
public class MyViewPager extends ViewPager {
    float lastMotionX = 0;
    int mActivePointerId = 0;
    private boolean mIsCanScroll=true;

    public boolean isCanLeftScroll() {
        return mIsCanLeftScroll;
    }

    public void setCanLeftScroll(boolean canLeftScroll) {
        mIsCanLeftScroll = canLeftScroll;
    }

    public boolean isCanScroll() {
        return mIsCanScroll;
    }

    public void setCanScroll(boolean canScroll) {
        mIsCanScroll = canScroll;
    }

    private boolean mIsCanLeftScroll=true;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void scrollTo(int x, int y) {
        if (mIsCanScroll) {
            super.scrollTo(x, y);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mIsCanLeftScroll){
            return super.onTouchEvent(ev);
        }else {
            switch (ev.getAction()){
                case MotionEvent.ACTION_DOWN:
                    int index = ev.getActionIndex();
                    mActivePointerId = ev.getPointerId(index);
                    lastMotionX=ev.getX(mActivePointerId);
                    super.onTouchEvent(ev);
                    break;
                case MotionEvent.ACTION_MOVE:
                    float x=ev.getX(mActivePointerId);
                    if(x<=lastMotionX){
                        lastMotionX=x;
                        return false;
                    }else {
                        return super.onTouchEvent(ev);
                    }
            }
            return super.onTouchEvent(ev);
        }
    }
}
