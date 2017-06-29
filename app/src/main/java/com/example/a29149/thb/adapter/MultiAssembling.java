package com.example.a29149.thb.adapter;

import android.widget.TextView;

import com.example.a29149.thb.R;

/**
 * Created by 张丽华 on 2017/6/29.
 * Description:
 */

public abstract class MultiAssembling extends Assembling<Integer, BaseViewHolder> {

    protected abstract String content(Integer integer);

    public TextView textView;
    @Override
    protected void convert(BaseViewHolder holder, Integer item, int position, boolean isScrolling) {
        textView = holder.findView(R.id.content);
        textView.setText(content(item));
    }
}
