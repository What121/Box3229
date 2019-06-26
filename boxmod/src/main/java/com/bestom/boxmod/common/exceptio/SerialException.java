package com.bestom.boxmod.common.exceptio;

public class SerialException extends Exception {

    public SerialException() {
        super();
    }

    public SerialException(String message) {
        super(message);
    }

    public SerialException(Throwable throwable) {
        super(throwable);
    }


    public SerialException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
