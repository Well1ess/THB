package com.example.a29149.thb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 张丽华 on 2017/6/29.
 * Description:
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private View convertView;
    public BaseViewHolder(View itemView) {
        super(itemView);
        convertView = itemView;
    }

    public <T> T findView(int resId){
        return (T) convertView.findViewById(resId);
    }
}
