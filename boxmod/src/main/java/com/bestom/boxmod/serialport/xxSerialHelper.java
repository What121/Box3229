package com.bestom.boxmod.serialport;

import com.bestom.boxmod.common.exceptio.SerialException;
import com.bestom.boxmod.common.utils.DataTurn;
import com.bestom.boxmod.common.utils.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import android_serialport_api.SerialPort;

/**
 * 串口辅助工具
 */
abstract class xxSerialHelper {
    private SerialPort mSerialPort;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private ReadThread mReadThread;
    //    private SendThread mSendThread;
    private String sPort = "/dev/ttyS1";
    private int iBaudRate = 115200;
    private boolean _isOpen = false;
    private byte[] _bLoopData = new byte[]{0x30};
    private int iDelay = 500;

    public xxSerialHelper(String sPort, int iBaudRate) {
        this.sPort = sPort;
        this.iBaudRate = iBaudRate;
    }

    public xxSerialHelper() {
        this("/dev/ttyS1", 115200);
    }

    public xxSerialHelper(String sPort) {
        this(sPort, 115200);
    }

    public xxSerialHelper(String sPort, String sBaudRate) {
        this(sPort, Integer.parseInt(sBaudRate));
    }

    /**********************************************************
     * open 串口
     */
    public void open() throws SecurityException, IOException,
            InvalidParameterException {
        mSerialPort = new SerialPort(new File(sPort), iBaudRate,0);
        mOutputStream = mSerialPort.getOutputStream();
        mInputStream = mSerialPort.getInputStream();
        mReadThread = new ReadThread();
        mReadThread.start();
//        mSendThread = new SendThread();
//        mSendThread.setSuspendFlag();
//        mSendThread.start();
        _isOpen = true;
    }

    /**********************************************************
     * close 串口
     */
    public void close() {
        if (mReadThread != null)
            mReadThread.interrupt();
        if (mSerialPort != null) {
            mSerialPort.close();
            mSerialPort = null;
        }
        _isOpen = false;
    }

    /**********************************************************
     * write byte to serial
     */
    public void send(final byte[] bOutArray) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        mOutputStream.write(bOutArray);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }).start();
    }

    /**********************************************************
     * write Hex to serial
     */
    public void sendHex(String sHex) throws SerialException {
        byte[] bOutArray = DataTurn.HexToByteArr(sHex);
        send(bOutArray);
    }

    /**********************************************************
     * write Txt to serial
     */
    public void sendTxt(String sTxt) {
        byte[] bOutArray = sTxt.getBytes();
        send(bOutArray);
    }

    /**********************************************************
     * read 线程
     */
    private class ReadThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
                try {
                    if (mInputStream == null) return;
                    byte[] buffer = new byte[100];
                    int size = mInputStream.read(buffer);
                    if (size > 0) {
                        byte[] bs = Util.subBytes(buffer, 0, size);
                        onDataReceived(bs, size);
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**********************************************************
     * write 线程
     */
    private class SendThread extends Thread {
        public boolean suspendFlag = true; // 控制线程的执行

        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
                synchronized (this) {
                    while (suspendFlag) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                send(getBLoopData());
                try {
                    Thread.sleep(iDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //线程暂停
        public void setSuspendFlag() {
            this.suspendFlag = true;
        }

        //唤醒线程
        public synchronized void setResume() {
            this.suspendFlag = false;
            notify();
        }
    }

    /**********************************************************
     * 获取波特率
     */
    public int getBaudRate() {
        return iBaudRate;
    }

    /**********************************************************
     * 设置波特率
     * @param iBaud 波特率
     */
    public boolean setBaudRate(int iBaud) {
        if (_isOpen) {
            return false;
        } else {
            iBaudRate = iBaud;
            return true;
        }
    }

    /**********************************************************
     * 设置波特率
     * @param sBaud 波特率
     */
    public boolean setBaudRate(String sBaud) {
        int iBaud = Integer.parseInt(sBaud);
        return setBaudRate(iBaud);
    }

    /**********************************************************
     * 获取串口号
     */
    public String getPort() {
        return sPort;
    }

    /**********************************************************
     * 设置串口号
     * @param sPort 端口
     */
    public boolean setPort(String sPort) {
        if (_isOpen) {
            return false;
        } else {
            this.sPort = sPort;
            return true;
        }
    }

    /**********************************************************
     * 判断串口是否打开
     */
    public boolean isOpen() {
        return _isOpen;
    }

    /**********************************************************
     * 获取Byte[]轮询数据
     */
    public byte[] getBLoopData() {
        return _bLoopData;
    }

    /**********************************************************
     * 设置Byte[]轮询数据
     */
    public void setBLoopData(byte[] bLoopData) {
        this._bLoopData = bLoopData;
    }

    /**********************************************************
     * 设置Txt轮询数据
     */
    public void setTxtLoopData(String sTxt) {
        this._bLoopData = sTxt.getBytes();
    }

    /**********************************************************
     * 设置Hex轮询数据
     */
    public void setHexLoopData(String sHex) {
        this._bLoopData = DataTurn.HexToByteArr(sHex);
    }

    /**********************************************************
     * 获取延迟时间
     */
    public int getDelay() {
        return iDelay;
    }

    /**********************************************************
     * 设置延迟时间
     */
    public void setDelay(int iDelay) {
        this.iDelay = iDelay;
    }

    /**********************************************************
     * 开启write串口线程
     */
//    public void startSend() {
//        if (mSendThread != null) {
//            mSendThread.setResume();
//        }
//    }

    /**********************************************************
     * 停止write串口线程
     */
//    public void stopSend() {
//        if (mSendThread != null) {
//            mSendThread.setSuspendFlag();
//        }
//    }

    /**********************************************************
     * read 串口数据返回
     */
    protected abstract void onDataReceived(byte[] buffer, int size);
}
