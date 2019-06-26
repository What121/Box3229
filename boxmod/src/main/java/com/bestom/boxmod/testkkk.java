package com.bestom.boxmod;

import java.io.File;
import java.io.IOException;

import android_serialport_api.SerialPort;

public class testkkk {
    private String sPort = "/dev/ttyS1";
    private int iBaudRate = 115200;


    public void open() throws IOException {
        SerialPort serialPort=new SerialPort(new File(sPort), iBaudRate,0);

    }

}
