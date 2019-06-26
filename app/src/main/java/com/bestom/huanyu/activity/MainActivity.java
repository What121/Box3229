package com.bestom.huanyu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.bestom.boxmod.api.Box;
import com.bestom.huanyu.MyActivity;
import com.bestom.huanyu.R;

public class MainActivity extends MyActivity implements View.OnClickListener {
    private Context mContext;
    private Activity mActivity;

    Button bt_apk,bt_powersetting,bt_gpio,bt_keyboard,bt_netcfg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        initview();

    }


    private void init(){
        mContext=this;
        mActivity=this;
    }

    private void initview(){
        bt_apk=findViewById(R.id.bt_apkmanager);
        bt_apk.setOnClickListener(this);
        bt_powersetting=findViewById(R.id.bt_turnoff);
        bt_powersetting.setOnClickListener(this);
        bt_gpio=findViewById(R.id.bt_gpio);
        bt_gpio.setOnClickListener(this);
        bt_keyboard=findViewById(R.id.bt_keycode);
        bt_keyboard.setOnClickListener(this);
        bt_netcfg=findViewById(R.id.bt_netcfg);
        bt_netcfg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_apkmanager:
                Intent apkintent=new Intent(mContext,ApkManagerActivity.class);
                startActivity(apkintent);
                break;
            case R.id.bt_turnoff:
                Intent turnsetting=new Intent(mContext, PowerActivity.class);
                startActivity(turnsetting);
                break;
            case R.id.bt_gpio:
                Intent gpiosetting =new Intent(mContext, GpioActivity.class);
                startActivity(gpiosetting);
                break;
            case R.id.bt_keycode:
                Intent keybord =new Intent(mContext, KeyActivity.class);
                startActivity(keybord);
                break;
            case R.id.bt_netcfg:
                Intent netcfg=new Intent(mContext,NetcfgActivity.class);
                startActivity(netcfg);
                break;
            default:
                break;
        }
    }


}
