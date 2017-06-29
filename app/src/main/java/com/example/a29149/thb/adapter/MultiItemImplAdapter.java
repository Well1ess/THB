package com.example.a29149.thb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.a29149.thb.R;

import java.util.List;

/**
 * Created by 张丽华 on 2017/6/29.
 * Description:
 */

public class MultiItemImplAdapter extends MultiItemAdapter<Integer, BaseViewHolder> {
    public MultiItemImplAdapter(RecyclerView recyclerView, Context context, List<Integer> mData) {
        super(recyclerView, context, mData);
        addItemType(0, R.layout.item1, FirstAssembling.class);
        addItemType(1, R.layout.item2, SecondAssembling.class);
    }

    @Override
    protected int createViewType(Integer item) {
        return item;
    }

    @Override
    protected String createKey(Integer item) {
        return item.toString();
    }
}
