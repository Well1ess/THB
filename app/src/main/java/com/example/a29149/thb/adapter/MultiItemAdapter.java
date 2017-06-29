package com.example.a29149.thb.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张丽华 on 2017/6/29.
 * Description:
 */

public abstract class MultiItemAdapter<T, K extends BaseViewHolder> extends BaseAdapter<T, K> {

    private SparseArray<Integer> layouts;
    private SparseArray<Class<? extends Assembling>> clazz;
    private SparseArray<Map<String, Assembling>> holders;

    protected abstract int createViewType(T item);

    protected abstract String createKey(T item);

    public MultiItemAdapter(RecyclerView recyclerView, Context context, List<T> mData) {
        super(recyclerView, context, mData);
    }

    protected void addItemType(int viewType, @LayoutRes int resId, Class<? extends Assembling> cla){
        if (layouts == null)
            layouts = new SparseArray<>();
        layouts.put(viewType, resId);

        if (clazz == null)
            clazz = new SparseArray<>();
        clazz.put(viewType, cla);

        if (holders == null)
            holders = new SparseArray<>();
        holders.put(viewType, new HashMap<String, Assembling>());
    }

    @Override
    protected int defViewType(int position) {
        return createViewType(mData.get(position));
    }

    @Override
    protected K createViewHolderByType(ViewGroup parent, int viewType) {
        return createViewHolderByType(LayoutInflater.from(parent.getContext()).inflate(getLayout(viewType), parent, false));
    }

    @Override
    protected void convert(K holder, T item, int position, boolean isScrolling) {
        int type = createViewType(item);
        String key = createKey(item);

        Assembling a = holders.get(type).get(key);
        if (a == null) {
            try {
                a = clazz.get(type).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            holders.get(type).put(key, a);
        }

        if (a != null) {
            a.convert(holder, item, position, isScrolling);
        }
    }

    private int getLayout(int viewType) {
        return layouts.get(viewType);
    }
}
