package com.bestom.huanyu.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bestom.boxmod.api.Box;
import com.bestom.huanyu.MyActivity;
import com.bestom.huanyu.R;

import static com.bestom.boxmod.common.constant.Const.LOW;
import static com.bestom.boxmod.common.constant.Const.UP;

public class GpioActivity extends MyActivity implements View.OnClickListener{
    private Context mContext;
    private Activity mActivity;

    private Box mBox;

    private Button bt_read,bt_green;
    private boolean readflag=false,greenflag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpio);

        init();
        initview();

    }

    private void init(){
        mContext=this;
        mActivity=this;

        mBox=new Box(mContext,mActivity);
    }

    private void initview(){
        bt_read=findViewById(R.id.switch_read);
        bt_read.setOnClickListener(this);
        bt_green=findViewById(R.id.switch_green);
        bt_green.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.switch_read:
                if (readflag){
                    mBox.read(LOW);
                    readflag=false;
                    bt_read.setText("开");

                }else {
                    mBox.read(UP);
                    readflag=true;
                    bt_read.setText("关");
                }
                break;
            case R.id.switch_green:
                if (greenflag){
                    mBox.green(LOW);
                    greenflag=false;
                    bt_green.setText("开");

                }else {
                    mBox.green(UP);
                    greenflag=true;
                    bt_green.setText("关");
                }
                break;
            default:
                break;
        }
    }
}

