package com.test.testgpio;

public class GPIO {
    // JNI
    public static native int nativeWriteGpio( int value);
    public static native int nativeReadGpio();

    public int writeGpioValue(int value)
    {
        return nativeWriteGpio(value);
    }

    public int readGpio()
    {
        return nativeReadGpio();
    }

    static
    {
        System.loadLibrary("GPIO");
    }
}
