package com.example.crxc.a360safe.main.HarassIntercept;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.bean.BlackInfo;
import com.example.crxc.a360safe.data.DataRepository;
import com.example.crxc.a360safe.data.DataSource;
import com.example.crxc.a360safe.main.Constant;
import com.example.crxc.a360safe.main.MyViewHolder;
import com.example.crxc.a360safe.util.L;
import com.example.crxc.a360safe.util.T;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by crxc on 2016/12/19.
 */

public class HarassInterceptPresenter implements HarassInterceptContract.Presenter, MyRecyclerAdapter.BindViewHolder ,MyRecyclerViewScrollListener.loadingMoreAble{
    private final HarassInterceptContract.View mView;
    private final DataRepository mDataRepository;
    private List<BlackInfo> mBlackInfos;
    private MyRecyclerAdapter mAdapter;

    public HarassInterceptPresenter(HarassInterceptContract.View view, DataRepository dataRepository) {
        mView = view;
        mDataRepository = dataRepository;
        mView.setPresenter(this);
    }

    @Override
    public void init() {
     mView.showProgressBar();
        final RecyclerView recyclerView=mView.getRv();
        recyclerView.setLayoutManager(new LinearLayoutManager(mView.getActivity()));
        Observable.create(new Observable.OnSubscribe<DataSource>() {
            @Override
            public void call(Subscriber<? super DataSource> subscriber) {
                mBlackInfos = mDataRepository.getBlackInfos(0,10);
                mAdapter = new MyRecyclerAdapter(mBlackInfos, HarassInterceptPresenter.this);
                subscriber.onNext(mDataRepository);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<DataSource>() {
            @Override
            public void call(DataSource dataSource) {
                recyclerView.setAdapter(mAdapter);
                mView.hideProgressBar();
                recyclerView.addOnScrollListener(new MyRecyclerViewScrollListener(HarassInterceptPresenter.this));
            }
        });
    }


    @Override
    public void addBlackList() {
        Activity activity = mView.getActivity();
        Intent intent = new Intent(activity, AddBlackListActivity_.class);
        activity.startActivity(intent);
    }

    @Override
    public void updateItem(Intent data) {
        int position = data.getExtras().getInt(Constant.bItemPosition);
        MyViewHolder holder = (MyViewHolder) mView.getRv().findViewHolderForAdapterPosition(position);
        TextView tv_phone = (TextView) holder.getViewByID(R.id.tv_phone);
        TextView tv_type = (TextView) holder.getViewByID(R.id.tv_type);
        BlackInfo blackInfo = mBlackInfos.get(position);
        tv_phone.setText(blackInfo.getMPhone());
        tv_type.setText(blackInfo.getMTypeDes());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        TextView tv_phone = (TextView) holder.getViewByID(R.id.tv_phone);
        TextView tv_type = (TextView) holder.getViewByID(R.id.tv_type);
        ImageView imageView= (ImageView) holder.getViewByID(R.id.iv_remove);
        final BlackInfo blackInfo = mBlackInfos.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataRepository.delete(blackInfo);
                mBlackInfos.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
//                mAdapter.notifyDataSetChanged();
//                T.show(mView.getActivity(),""+position,0);
            }
        });
        tv_phone.setText(blackInfo.getMPhone());
        L.d(blackInfo.getMPhone());
        tv_type.setText(blackInfo.getMTypeDes());
        holder.getV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mView.getActivity(),AddBlackListActivity_.class);
                intent.setAction(Constant.aUpdateBlackList);
                Bundle bundle=new Bundle();
                bundle.putInt(Constant.bItemPosition,position);
                intent.putExtras(bundle);
                mView.startActivityForResult(intent,Constant.rqEditBlackList);
            }
        });
    }

    @Override
    public void loadingMore() {
        mView.showProgressBar();
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                mDataRepository.loadingData(20);
                SystemClock.sleep(2000);
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        mAdapter.notifyDataSetChanged();
                        mView.hideProgressBar();
                    }
                });
    }
}
