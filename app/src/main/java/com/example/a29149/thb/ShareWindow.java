package com.example.a29149.thb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张丽华 on 2017/6/19.
 * Description:
 */

public class ShareWindow extends PopupWindow {
    public ShareWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public static class Builder {
        private ShareWindow mShareWindow;
        private List<SharePlatform> sharePlatformList = new ArrayList<>();
        private Context mContext;
        private OnClickListener onClickListener;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder addPlatform(SharePlatform sharePlatform) {
            for (SharePlatform sharePlatform1 : sharePlatformList) {
                if (sharePlatform1.getResId() == sharePlatform.getResId()) {
                    return this;
                }
            }

            sharePlatformList.add(sharePlatform);
            return this;
        }

        public Builder setClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public PopupWindow create() {
            View view = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
            mShareWindow = new ShareWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ListView listView = (ListView) view.findViewById(R.id.lv_shareplatform);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (onClickListener != null){
                        onClickListener.onClick(mShareWindow, position);
                    }
                }
            });
            mShareWindow.setOutsideTouchable(true);
//            ((Activity)mContext).getWindow().setAl
            return mShareWindow;
        }
    }

    public interface OnClickListener {
        void onClick(PopupWindow popupWindow, int position);
    }
}
