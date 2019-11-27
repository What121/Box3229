package com.bestom.boxmod.api;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;

import com.bestom.boxmod.manager.ApkManager;
import com.bestom.boxmod.manager.GpioManager;
import com.bestom.boxmod.manager.KeyManager;
import com.bestom.boxmod.manager.NetStaticManager;
import com.bestom.boxmod.manager.PackageInfoManager;
import com.bestom.boxmod.manager.SleepManager;
import com.bestom.boxmod.manager.WakeupManager;

import java.util.List;

import static com.bestom.boxmod.common.constant.Const.LOW;
import static com.bestom.boxmod.common.constant.Const.UP;

public class Box {
    private Context mContext;
    private Activity mActivity;

    private final String TAG=this.getClass().getSimpleName();

    public Box(Context context, Activity activity) {
        mContext=context;
        mActivity=activity;
    }

    //定时关机
    public void Wakeup(long starttime){
        repeatWakeup(starttime,false);
    }

    //循环定时关机
    public void repeatWakeup(long starttime,boolean flag){
        if (flag){
            WakeupManager.getInstance(mContext).RepeatWakeup(starttime);
        }else {
            WakeupManager.getInstance(mContext).wakeup(starttime);
        }
    }

    //休眠
    public void sleep(long starttime){
        repeatSleep(starttime,false);
    }

    //循环定时休眠
    public void repeatSleep(long starttime,boolean flag){
        if (flag){
            SleepManager.getInstance(mContext).RepeatSleep(starttime);
        }else {
            SleepManager.getInstance(mContext).sleep(starttime);
        }
    }

//    //唤醒
//    public void wakeup(long starttime){
//        repeatWakeup(starttime,false);
//
//    }
//
//    //循环定时关机
//    public void repeatWakeup(long starttime,boolean flag){
//        if (flag){
//            SleepManager.getInstance(mContext).RepeatWakeup(starttime);
//        }else {
//            SleepManager.getInstance(mContext).wakeup(starttime);
//        }
//
//    }

    //获取所有应用
    public List<PackageInfo> getAllApps(){
        return PackageInfoManager.getInstance(mContext).getAllApps();
    }

    //通过包名启动第三方应用
    public void startThridApp(String packageName){
        PackageInfoManager.getInstance(mContext).startThridApp(packageName);
    }

    //静默安装
    public boolean install(String apkPath){
        return ApkManager.getInstance().install(apkPath);
    }

    //静默卸载
    public boolean uninstall(String packageName){
        return ApkManager.getInstance().uninstall(packageName);
    }

    //红灯控制
    public void read(int i){
        if (i==UP){
            GpioManager.getInstance().writevalue(1);
        }else if (i==LOW){
            GpioManager.getInstance().writevalue(2);
        }
    }

    //绿灯控制
    public void green(int i){
        if (i==UP){
            GpioManager.getInstance().writevalue(3);
        }else if (i==LOW){
            GpioManager.getInstance().writevalue(4);
        }
    }

    public String KeyValues(int keycode){
        return KeyManager.getInstance().KeyValues(keycode);
    }

    public void wificfg(){
        NetStaticManager.getInstance(mContext,mActivity).setWIFI_Static();
    }

    public void ethcfg(){
        NetStaticManager.getInstance(mContext,mActivity).setETH_Static();
    }
}
