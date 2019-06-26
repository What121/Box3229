package com.bestom.boxmod.manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import static android.content.Context.ALARM_SERVICE;

public class WakeupManager {
    private AlarmManager alarmManager;
    private PendingIntent pi;
    public static WakeupManager instance;

    public static WakeupManager getInstance(Context mContext) {
        if (instance == null) {
            synchronized (WakeupManager.class) {
                if (instance == null) {
                    instance = new WakeupManager(mContext);
                }
            }
        }
        return instance;
    }

    private WakeupManager(Context mContext) {
        alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent();
        intent.setAction("com.huanyu.setting.wakeup");
        pi = PendingIntent.getBroadcast(mContext, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public void wakeup(long starttime){
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,  starttime, pi);
    }

    public void RepeatWakeup(long starttime){
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,starttime,24*60*60*1000,pi);
    }

}
