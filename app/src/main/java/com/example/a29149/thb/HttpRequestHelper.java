package com.example.a29149.thb;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 张丽华 on 2017/6/19.
 * Description :
 */

public class HttpRequestHelper<T> {
    public void getResult(String url, Handler handler){
        Message message = new Message();
        message.obj = new ArrayList<>();
        message.what = RESULT_OK;

        handler.sendMessage(message);
    }
}
