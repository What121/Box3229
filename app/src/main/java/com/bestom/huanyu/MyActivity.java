package com.bestom.huanyu;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.bestom.boxmod.api.Box;

public class MyActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private Box mBox;
    public String keyvalues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init(){
        mContext=this;
        mActivity=this;

        mBox=new Box(mContext,mActivity);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_UP){
            keyvalues=mBox.KeyValues(event.getKeyCode());
            Log.i("*******","keycode:"+event.getKeyCode());
            Log.i("*******","keyvalue:"+mBox.KeyValues(event.getKeyCode()));
        }
        return super.dispatchKeyEvent(event);
    }

}
