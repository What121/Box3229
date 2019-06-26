package com.bestom.boxmod.manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import static android.content.Context.ALARM_SERVICE;

public class SleepManager {
    private AlarmManager alarmManager;
    private PendingIntent pi_sleep;
    public static SleepManager instance;

    public static SleepManager getInstance(Context mContext) {
        if (instance == null) {
            synchronized (SleepManager.class) {
                if (instance == null) {
                    instance = new SleepManager(mContext);
                }
            }
        }
        return instance;
    }

    private SleepManager(Context mContext) {
        alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
        Intent sleep = new Intent("com.huanyu.setting.sleep");
        pi_sleep = PendingIntent.getBroadcast(mContext, 0,
                sleep, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public void sleep(long starttime){
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,  starttime, pi_sleep);
    }

    public void RepeatSleep(long starttime){
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,starttime,24*60*60*1000,pi_sleep);
    }

}
