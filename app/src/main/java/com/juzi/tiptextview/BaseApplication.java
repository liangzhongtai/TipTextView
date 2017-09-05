package com.juzi.tiptextview;

import android.app.Application;

import com.juzi.tiptextview.global.Global;

/**
 * Created by liangzhongtai on 2017/2/5.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Global.init(this);
    }
}
