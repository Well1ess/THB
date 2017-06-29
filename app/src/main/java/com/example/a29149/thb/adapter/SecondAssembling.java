package com.example.a29149.thb.adapter;

import android.widget.TextView;

/**
 * Created by 张丽华 on 2017/6/29.
 * Description:
 */

public class SecondAssembling extends MultiAssembling {
    public TextView textView;

    @Override
    protected String content(Integer integer) {
        return "测试" + SecondAssembling.class.getSimpleName();
    }
}
