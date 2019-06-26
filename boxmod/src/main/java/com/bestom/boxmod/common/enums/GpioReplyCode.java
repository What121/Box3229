package com.bestom.boxmod.common.enums;

public enum GpioReplyCode {
    VALUE_ERROR(-99,"电平输入有误"),
    WRITE_ERROR(-2,"写失败"),
    OPEN_ERROR(-1,"打开IO口失败"),
    SUCCESS_ERROR(0,"成功");

    private final int code;

    private final String msg;

    GpioReplyCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
