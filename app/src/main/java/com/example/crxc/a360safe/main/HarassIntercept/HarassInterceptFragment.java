package com.example.crxc.a360safe.main.HarassIntercept;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.data.DataRepository;
import com.example.crxc.a360safe.main.Constant;
import com.example.crxc.a360safe.main.MyViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import okhttp3.Response;

/**
 * Created by crxc on 2016/12/19.
 */
@EFragment(R.layout.fragment_harass_intercept)
public class HarassInterceptFragment extends Fragment implements HarassInterceptContract.View{

    @ViewById(R.id.rv)
    RecyclerView mRecyclerView;
    @ViewById(R.id.tv_title)
    TextView mTextView;
    @ViewById(R.id.progress)
    ProgressBar mProgressBar;
    private HarassInterceptContract.Presenter mPresenter;

    public HarassInterceptFragment() {
    }
    @AfterViews
    void afterViews(){
        mPresenter.init();
    }

    @Click(R.id.iv_black_list_add)
    void addBlackList(){
        mPresenter.addBlackList();
    }
    @Override
    public void setPresenter(HarassInterceptContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public RecyclerView getRv() {
        return mRecyclerView;
    }

    @Override
    public void setTitle(String s) {
        mTextView.setText(s);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_animation);
        mProgressBar.startAnimation(animation);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Constant.rcEditBlackList){
            mPresenter.updateItem(data);
        }
    }
}
