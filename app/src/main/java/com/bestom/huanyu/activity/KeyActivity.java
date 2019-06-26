package com.bestom.huanyu.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.bestom.boxmod.api.Box;
import com.bestom.huanyu.MyActivity;
import com.bestom.huanyu.R;

public class KeyActivity extends MyActivity {
    private Context mContext;
    private Activity mActivity;

    TextView TV_keyvalues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);

        init();
        initview();

    }

    private void init(){
        mContext=this;
        mActivity=this;
    }

    private void initview(){
        TV_keyvalues=findViewById(R.id.key_values);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        TV_keyvalues.setText(this.keyvalues);
        return super.onKeyUp(keyCode, event);
    }

}
