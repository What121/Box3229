package com.bestom.huanyu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bestom.boxmod.api.Box;
import com.bestom.huanyu.MyActivity;
import com.bestom.huanyu.R;

import java.io.File;


public class ApkManagerActivity extends MyActivity implements View.OnClickListener {
    private Context mContext;
    private Activity mActivity;

    private Box mBox;

    Button bt_choosefile,bt_silentinstall,bt_apklist;
    TextView tv_filepath;

    private String apkPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkmanager);


        init();
        initview();

        //new GPIO().writeGpioValue(1);
    }

    private void init(){
        mContext=this;
        mActivity=this;

        mBox=new Box(mContext,mActivity);
    }

    private void initview(){
        bt_choosefile=findViewById(R.id.bt_chooseapk);
        bt_choosefile.setOnClickListener(this);
        bt_silentinstall=findViewById(R.id.bt_SilentInstall);
        bt_silentinstall.setOnClickListener(this);
        tv_filepath=findViewById(R.id.apkPathText);
        bt_apklist=findViewById(R.id.bt_listapk);
        bt_apklist.setOnClickListener(this);
    }

    /**
     * 判断手机是否拥有Root权限。
     * @return 有root权限返回true，否则返回false。
     */
    public boolean isRoot() {
        boolean bool = false;
        try {
            if (new File("/system/bin/su").exists()){
                bool=true;
            }else if (new File("/system/xbin/su").exists()){
                bool=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    private void silentinstall(){
        if (!isRoot()) {
            Toast.makeText(this, "没有ROOT权限，不能使用秒装", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(apkPath)) {
            Toast.makeText(this, "请选择安装包！", Toast.LENGTH_SHORT).show();
            return;
        }
        bt_silentinstall.setText("安装中");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("apkpath",apkPath);
                final boolean result=mBox.install(apkPath);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result) {
                            Toast.makeText(ApkManagerActivity.this, "安装成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ApkManagerActivity.this, "安装失败！", Toast.LENGTH_SHORT).show();
                        }
                        bt_silentinstall.setText("秒装");
                    }
                });

            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ((requestCode==0)&&(resultCode==RESULT_OK)){
            apkPath = data.getStringExtra("apk_path");
            tv_filepath.setText(apkPath);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_chooseapk:
                Intent filechoseintent=new Intent(this, FilechoseActivity.class);
                startActivityForResult(filechoseintent, 0);
                break;
            case R.id.bt_SilentInstall:
                silentinstall();
                break;
            case R.id.bt_listapk:
                Intent apkintent=new Intent(this, ApklistActivity.class);
                startActivity(apkintent);
                break;
            default:
                break;
        }
    }

}
