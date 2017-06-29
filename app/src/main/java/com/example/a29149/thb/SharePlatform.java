package com.example.a29149.thb;

/**
 * Created by 张丽华 on 2017/6/19.
 * Description:
 */

public class SharePlatform {
    private int resId;
    private String platformName;

    public SharePlatform(int resId, String platformName) {
        this.resId = resId;
        this.platformName = platformName;
    }

    public int getResId() {
        return resId;
    }

    public String getPlatformName() {
        return platformName;
    }
}
