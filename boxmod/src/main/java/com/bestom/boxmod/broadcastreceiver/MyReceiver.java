package com.bestom.boxmod.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;

public class MyReceiver extends BroadcastReceiver {
    PowerManager mPowerManager;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyReceiver receive:",intent.getAction());
        switch (intent.getAction()){
            case "com.huanyu.setting.shutdown":
                Intent shutdown = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
                //true 弹窗是否关机窗口
                shutdown.putExtra("android.intent.extra.KEY_CONFIRM", false);
                shutdown.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(shutdown);
                break;
            case "com.huanyu.setting.sleep":
                mPowerManager= (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                try {
                    mPowerManager.getClass().getMethod("goToSleep", new Class[]{long.class}).invoke(mPowerManager, SystemClock.uptimeMillis());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                break;
            case "com.huanyu.setting.wakeup":
                mPowerManager= (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                try {
                    mPowerManager.getClass().getMethod("wakeUp", new Class[]{long.class}).invoke(mPowerManager, SystemClock.uptimeMillis());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }
}
