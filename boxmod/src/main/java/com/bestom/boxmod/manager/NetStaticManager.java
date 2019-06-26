package com.bestom.boxmod.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class NetStaticManager {
    private Context mContext;
    private Activity mActivity;
    @SuppressLint("StaticFieldLeak")
    public static NetStaticManager instance;

    public static NetStaticManager getInstance(Context mContext,Activity mActivity) {
        if (instance == null) {
            synchronized (NetStaticManager.class) {
                if (instance == null) {
                    instance = new NetStaticManager(mContext,mActivity);
                }
            }
        }
        return instance;
    }

    private NetStaticManager(Context context,Activity activity) {
        mContext=context;
        mActivity=activity;
    }

    //wifi网络设置
    public void setWIFI_Static(){
        mContext.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }

    //有线网络设置
    public void setETH_Static(){
        Intent netintent=new Intent();
        netintent.setClassName("com.rk_itvui.settings",
                "com.rk_itvui.settings.network.WireNetworkSetting");
        mContext.startActivity(netintent);

    }

}
