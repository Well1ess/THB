package com.example.a29149.thb.adapter;

/**
 * Created by 张丽华 on 2017/6/29.
 * Description:
 */

public class FirstAssembling extends MultiAssembling {

    @Override
    protected String content(Integer integer) {
        return "测试" + FirstAssembling.class.getSimpleName();
    }
}
