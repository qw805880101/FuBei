package com.example.fubei.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.fubei.Http.HttpConnection;
import com.example.fubei.utils.Info;

/**
 * Created by tc on 2016/11/25.
 */

public abstract class BaseActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Info.currentActivity = this;
        Info.currentContext = this;
    }

    @Override
    protected void onDestroy() {
        HttpConnection.isDestroy = false;
        super.onDestroy();
    }
}
