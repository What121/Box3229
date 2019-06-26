package com.bestom.huanyu.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bestom.boxmod.api.Box;
import com.bestom.huanyu.MyActivity;
import com.bestom.huanyu.R;

import java.util.Calendar;

public class PowerActivity extends MyActivity implements View.OnClickListener {
    private Context mContext;
    private Activity mActivity;

    private Box mBox;

    TimePicker mSleepTimePicker,mWakeupTimePicker;
    TextView mSleeptimetv,mWakeuptimetv;
    CheckBox mFunCheck_sleep,mFunCheck_wakeup;
    Button msleep_bt,mwakeup_bt;

    private Calendar sleep=null;
    private Calendar wakeup=null;

    private boolean flag_sleep=false;
    private boolean flag_wakeup=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powermg);

        init();

        initview();

    }

    private void init(){
        mContext=this;
        mActivity=this;

        mBox=new Box(mContext,mActivity);
    }

    private void initview(){
        mSleepTimePicker=findViewById(R.id.timepicker_sleep);
        mSleepTimePicker.setIs24HourView(true);
        mSleepTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                //设置当前时间
                sleep = Calendar.getInstance();
                sleep.setTimeInMillis(System.currentTimeMillis());
                // 根据用户选择的时间来设置Calendar对象
                sleep.set(Calendar.HOUR_OF_DAY, i);
                sleep.set(Calendar.MINUTE, i1);
                mSleeptimetv.setText(sleep.getTimeInMillis()+"");
            }
        });

        mSleeptimetv=findViewById(R.id.tv_sleep);
        mFunCheck_sleep=findViewById(R.id.fun_sleep);
        mFunCheck_sleep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mFunCheck_sleep.setChecked(isChecked);
                flag_sleep=isChecked;
            }
        });
        msleep_bt=findViewById(R.id.bt_sleep);
        msleep_bt.setOnClickListener(this);

        mWakeupTimePicker=findViewById(R.id.timepicker_wakeup);
        mWakeupTimePicker.setIs24HourView(true);
        mWakeupTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                //设置当前时间
                wakeup = Calendar.getInstance();
                wakeup.setTimeInMillis(System.currentTimeMillis());
                // 根据用户选择的时间来设置Calendar对象
                wakeup.set(Calendar.HOUR_OF_DAY, i);
                wakeup.set(Calendar.MINUTE, i1);
                mWakeuptimetv.setText(wakeup.getTimeInMillis()+"");
            }
        });
        mWakeuptimetv=findViewById(R.id.tv_wakeup);
        mFunCheck_wakeup=findViewById(R.id.fun_wakeup);
        mFunCheck_wakeup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mFunCheck_wakeup.setChecked(isChecked);
                flag_wakeup=isChecked;
            }
        });
        mwakeup_bt=findViewById(R.id.bt_wakeup);
        mwakeup_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_sleep:
                if (sleep!=null){
                    long starttime=sleep.getTimeInMillis();
                    if (flag_sleep){
                        //循环定时关机
                        //24h 24*60*60*1000
                        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,starttime,24*60*60*1000,pi);
                        mBox.repeatSleep(starttime,flag_sleep);
                    }else {
                        //alarmManager.setExact(AlarmManager.RTC_WAKEUP,  starttime, pi);
                        mBox.sleep(starttime);
                    }
                    Toast.makeText(mContext,"定时关机设置成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext,"请选择定时关机时间",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_wakeup:
                if (wakeup!=null){
                    long starttime=wakeup.getTimeInMillis();
                    if (flag_wakeup){
                        //循环定时开机
                        //24h 24*60*60*1000
                        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,starttime,24*60*60*1000,pi);
                        mBox.repeatWakeup(starttime,flag_wakeup);
                    }else {
                        //alarmManager.setExact(AlarmManager.RTC_WAKEUP,  starttime, pi);
                        mBox.Wakeup(starttime);
                    }
                    Toast.makeText(mContext,"定时开机设置成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext,"请选择定时开机时间",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

}
