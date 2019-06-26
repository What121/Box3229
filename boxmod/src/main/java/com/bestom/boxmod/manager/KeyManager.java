package com.bestom.boxmod.manager;

import android.util.Log;
import android.view.KeyEvent;

import static android.content.ContentValues.TAG;
import static android.view.KeyEvent.KEYCODE_VOLUME_MUTE;

public class KeyManager {
    private static KeyManager instance;

    public static KeyManager getInstance() {
        if (instance == null) {
            synchronized (KeyManager.class) {
                if (instance == null) {
                    instance = new KeyManager();
                }
            }
        }
        return instance;
    }

    private KeyManager() {

    }

    public String KeyValues(int keycode){
        String keyvalue="";
        switch (keycode){
            case KeyEvent.KEYCODE_ENTER:     //确定键enter
            case KeyEvent.KEYCODE_DPAD_CENTER:
                keyvalue="enter--->";
                break;
            case KeyEvent.KEYCODE_BACK:    //返回键
                keyvalue="back--->";
                break;   //这里由于break会退出，所以我们自己要处理掉 不返回上一层
            case KeyEvent.KEYCODE_SETTINGS: //设置键
                keyvalue="setting--->";
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:   //向下键
                keyvalue="down--->";
                break;
            case KeyEvent.KEYCODE_DPAD_UP:   //向上键
                keyvalue="up--->";
                break;
            case KeyEvent.KEYCODE_0:   //数字键0
                keyvalue="0--->";
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT: //向左键
                keyvalue="left--->";
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:  //向右键
                keyvalue="right--->";
                break;
            case KeyEvent.KEYCODE_INFO:    //info键
                keyvalue="info--->";
                break;
            case KeyEvent.KEYCODE_PAGE_DOWN:     //向上翻页键
            case KeyEvent.KEYCODE_MEDIA_NEXT:
                keyvalue="page down--->";
                break;
            case KeyEvent.KEYCODE_PAGE_UP:     //向下翻页键
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                keyvalue="page up--->";
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:   //调大声音键
                keyvalue="voice up--->";
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN: //降低声音键
                keyvalue="voice down--->";
                break;
            case KeyEvent.KEYCODE_VOLUME_MUTE: //禁用声音
                keyvalue="voice mute--->";
                break;
            default:
                break;
        }
    return keyvalue;
    }

}
