package com.bestom.boxmod.interfa.callback;

public interface RespSampleListener<T> {

    void onSuccess(int code, T t);

    void onFailure(int code, String errMsg);
}
