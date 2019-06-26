package com.bestom.boxmod.serialport;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.bestom.boxmod.Dataservice.xxobservable.DataObservable;
import com.bestom.boxmod.common.constant.Const;
import com.bestom.boxmod.common.exceptio.SerialException;
import com.bestom.boxmod.common.utils.DataTurn;
import com.bestom.boxmod.common.utils.FLogs;

import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * 串口管理类
 */
public class xxSerialManager extends xxSerialHelper {
    private static final String TAG = "Camera Serial";
    private static volatile xxSerialManager instance;
    private static final int SEND_WHAT = 1;

    private String sPort = "/dev/ttyS1";
    private int iBaudRate = 115200;
    //private int iBaudRate = 9600;

    private String rData = "";
    private StringBuilder dataBuilder;

    private MyHandler mHandler;

    public xxSerialManager() {
        mHandler = new MyHandler();
        dataBuilder = new StringBuilder();
        config(sPort,iBaudRate);
    }

    public static xxSerialManager getInstance() {
        if (instance == null) {
            synchronized (xxSerialManager.class) {
                if (instance == null) {
                    instance = new xxSerialManager();
                }
            }
        }
        return instance;
    }

    /**********************************************************************
     * 配置串口
     * @param port 串口号
     * @param iBaud 波特率
     */
    public void config(String port, int iBaud) {
        this.sPort = port;
        this.iBaudRate = iBaud;
        if (isOpen()) {
            setPort(sPort);
            setBaudRate(iBaudRate);
        }
    }

    /**********************************************************************
     * 打开串口
     */
    public void turnOn() {
        try {
            open();
            setPort(sPort);
            setBaudRate(iBaudRate);
        } catch (SecurityException e) {
            FLogs.e(TAG, "打开串口失败:没有串口读/写权限!");
        } catch (IOException e) {
            FLogs.e(TAG, "打开串口失败:未知错误!");
        } catch (InvalidParameterException e) {
            FLogs.e(TAG, "打开串口失败:参数错误!");
        }
    }

    /**********************************************************************
     * 关闭串口
     */
    public void turnOff() {
        //stopSend();
        close();
    }

    /**********************************************************************
     * 向串口发送16进制字符串数据
     * @param sHex 16进制的指令字符串
     */
    public void sendHex(String sHex) throws SerialException {
        if (!isOpen()) {
            throw new SerialException("请打开串口，再发送数据！");
        }
        super.sendHex(sHex);
    }

    @Override
    protected void onDataReceived(byte[] buffer, int size) {
        dataBuilder.append(DataTurn.ByteArrToHex(buffer));
        if (dataBuilder.indexOf(Const.MAGIC) == 0) {
            if (dataBuilder.length() >= 32) {
                String body_size_Hex = dataBuilder.substring(24, 32);
                int body_size = DataTurn.HexToInt(body_size_Hex);
                int all_size = 32 + body_size * 2;
                if (dataBuilder.length() >= all_size) {
                    //FLogs.d(TAG, "DATA HEX:" + dataBuilder.substring(0, all_size));
                    Message msg = new Message();
                    msg.what = SEND_WHAT;
                    msg.obj = dataBuilder.substring(0, all_size);
                    mHandler.sendMessage(msg);
                    dataBuilder.delete(0, all_size);
                }
            }
        } else if (dataBuilder.length() > Const.MAGIC.length()) {
            FLogs.e(TAG, "ERROR-Camera DATA HEX：" + dataBuilder);
            dataBuilder.setLength(0); //清空
        }
    }

    @SuppressLint("HandlerLeak")
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SEND_WHAT) {
                // 设置到被观察者,通知更新
                DataObservable.getInstance().setInfo(String.valueOf(msg.obj));
            }
        }
    }
}
