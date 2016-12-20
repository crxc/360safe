package com.example.crxc.a360safe.main.HarassIntercept;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by crxc on 2016/12/20.
 */

public class MyRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private final loadingMoreAble mLoadingMoreAble;

    public MyRecyclerViewScrollListener(loadingMoreAble loadingMoreAble) {
        super();
        mLoadingMoreAble = loadingMoreAble;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager layoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
        if(newState==RecyclerView.SCROLL_STATE_IDLE){
            int itemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
            int totalCount=layoutManager.getItemCount();
            if(itemPosition==totalCount-1){
                //加载更多
                mLoadingMoreAble.loadingMore();
            }
        }

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }
    public interface loadingMoreAble {
        void loadingMore();
    }
}
