package com.example.a29149.thb.adapter;

/**
 * Created by 张丽华 on 2017/6/29.
 * Description:
 */

public abstract class Assembling<T, K extends BaseViewHolder> {
    protected abstract void convert(K holder, T item, int position, boolean isScrolling);
}
