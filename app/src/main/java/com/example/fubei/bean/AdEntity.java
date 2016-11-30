package com.example.fubei.bean;

/**
 * Created by tc on 2016/11/23.
 */

public class AdEntity {
    private String mFront; //前面的文字
    private String mBack; //后面的文字
    private String mUrl;//包含的链接

    public AdEntity(String mFront, String mBack, String mUrl) {
        this.mFront = mFront;
        this.mBack = mBack;
        this.mUrl = mUrl;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmFront() {
        return mFront;
    }

    public void setmFront(String mFront) {
        this.mFront = mFront;
    }

    public String getmBack() {
        return mBack;
    }

    public void setmBack(String mBack) {
        this.mBack = mBack;
    }
}
