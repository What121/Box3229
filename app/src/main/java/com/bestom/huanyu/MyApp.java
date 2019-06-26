package com.bestom.huanyu;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.KeyEvent;

public class MyApp extends Application {
    private Context mContext;
    private Activity mActivity;

    @Override
    public void onCreate() {
        super.onCreate();

        init();

    }

    private void init(){
        mContext=this;

    }


}
