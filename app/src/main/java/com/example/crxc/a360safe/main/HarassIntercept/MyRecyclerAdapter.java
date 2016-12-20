package com.example.crxc.a360safe.main.HarassIntercept;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crxc.a360safe.App;
import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.bean.BlackInfo;
import com.example.crxc.a360safe.main.Constant;
import com.example.crxc.a360safe.main.MyViewHolder;
import com.example.crxc.a360safe.util.L;

import java.util.List;
import java.util.zip.Inflater;

import static android.R.attr.fragment;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by crxc on 2016/12/19.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final BindViewHolder mBindViewHolder;
    private List<BlackInfo> mBlackInfos;

    public MyRecyclerAdapter(List<BlackInfo> blackInfos, BindViewHolder bindViewHolder) {
        mBlackInfos = blackInfos;
        mBindViewHolder=bindViewHolder;
        L.d(mBlackInfos.size()+"个数据");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(App.getApp());
        View view = inflater.inflate(R.layout.activity_harass_rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mBindViewHolder.onBindViewHolder(holder,position);
    }
    public interface BindViewHolder{
        void onBindViewHolder(MyViewHolder holder, final int position);
    }
    @Override
    public int getItemCount() {
        return mBlackInfos.size();
    }
}
