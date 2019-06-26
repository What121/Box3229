package com.bestom.huanyu.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bestom.boxmod.api.Box;
import com.bestom.boxmod.common.bean.netcfg;
import com.bestom.boxmod.manager.NetStaticManager;
import com.bestom.huanyu.MyActivity;
import com.bestom.huanyu.R;

public class NetcfgActivity extends MyActivity implements View.OnClickListener{
    private Context mContext;
    private Activity mActivity;
    private Box mBox;

    Button bt_wifi,bt_eth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netcfg);

        init();
        initview();

    }

    private void init(){
        mContext=this;
        mActivity=this;
        mBox=new Box(mContext,mActivity);
    }

    private void initview(){
        bt_wifi=findViewById(R.id.bt_wifi);
        bt_wifi.setOnClickListener(this);
        bt_eth=findViewById(R.id.bt_eth);
        bt_eth.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_wifi:
                mBox.wificfg();
                break;
            case R.id.bt_eth:
                mBox.ethcfg();
                break;
            default:
                break;
        }
    }
}
