package com.example.crxc.a360safe.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crxc.a360safe.App;
import com.example.crxc.a360safe.R;

/**
 * Created by crxc on 2016/12/9.
 */

public class MyRecylerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private static final int[] IMGRESID = { R.mipmap.sjfd, R.mipmap.srlj, R.mipmap.rjgj, R.mipmap.jcgl,
            R.mipmap.lltj, R.mipmap.sjsd, R.mipmap.hcql, R.mipmap.cygj };
    private static final String[] TITLES = { "手机防盗", "骚扰拦截", "软件管家", "进程管理", "流量统计", "手机杀毒", "缓存清理", "常用工具" };
    private static final String[] DES = { "远程定位", "全面骚扰拦截", "管理您的软件", "管理运行进程", "流量一目了然", "病毒无处藏身", "系统快如火箭", "工具大全" };

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(App.getApp());
        View view = inflater.inflate(R.layout.fragment_main_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageView imageView = (ImageView) holder.getViewByID(R.id.iv);
        TextView title= (TextView) holder.getViewByID(R.id.tv_title);
        TextView des= (TextView) holder.getViewByID(R.id.tv_des);
        imageView.setImageResource(IMGRESID[position]);
        title.setText(TITLES[position]);
        des.setText(DES[position]);
    }

    @Override
    public int getItemCount() {
        return IMGRESID.length;
    }
}
