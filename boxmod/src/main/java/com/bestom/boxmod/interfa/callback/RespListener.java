package com.bestom.boxmod.interfa.callback;

public interface RespListener {

    void onSuccess(int code, String msg);

    void onFailure(int code, String errMsg);
}
