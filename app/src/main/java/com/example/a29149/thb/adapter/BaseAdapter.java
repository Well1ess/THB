package com.example.a29149.thb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by 张丽华 on 2017/6/29.
 * Description:
 */

public abstract class BaseAdapter<T, K extends BaseViewHolder> extends RecyclerView.Adapter<K> {

    protected List<T> mData;
    private Context mContext;
    private boolean isScrolling = false;

    protected abstract int defViewType(int position);

    protected abstract K createViewHolderByType(ViewGroup parent, int viewType);

    protected abstract void convert(K holder, T item, int position, boolean isScrolling);

    public BaseAdapter(RecyclerView recyclerView, Context context, List<T> mData) {
        this.mData = mData == null ? new ArrayList<T>() : mData;
        this.mContext = context;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                isScrolling = newState != SCROLL_STATE_IDLE;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return defViewType(position);
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolderByType(parent, viewType);
    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        convert(holder,mData.get(position), position, isScrolling);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected K createViewHolderByType(View view){
        return ((K)new BaseViewHolder(view));
    }
}
