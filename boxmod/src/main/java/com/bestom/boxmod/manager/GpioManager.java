package com.bestom.boxmod.manager;

import com.test.testgpio.GPIO;

/**
 * GPIO
 * 初始化入口
 */
public class GpioManager {
    private static final String TAG= GpioManager.class.getSimpleName();
    private GPIO mGPIO;
    private static GpioManager instance;

    public static GpioManager getInstance() {
        if (instance == null) {
            synchronized (GpioManager.class) {
                if (instance == null) {
                    instance = new GpioManager();
                }
            }
        }
        return instance;
    }

    private GpioManager() {
        mGPIO=new GPIO();
    }

    public int writevalue(int value){
        return mGPIO.writeGpioValue(value);
    }

    public int readvalue(){
        return mGPIO.readGpio();
    }

}
