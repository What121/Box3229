package com.bestom.boxmod.Dataservice;

import com.bestom.boxmod.interfa.callback.DataReceivedListener;
import com.bestom.boxmod.interfa.service.Observer;

/**
 * 观察者，接收串口数据 通知服务
 */
public class DataObserver implements Observer {

    private DataReceivedListener dataReceivedListener;

    /**
     * 对数据观察者设置监听
     * @param dataReceivedListener 监听
     */
    public void setOnDataReceivedListener(DataReceivedListener dataReceivedListener){
        this.dataReceivedListener=dataReceivedListener;
    }

    @Override
    public void dataReceived(String dataHex) {
        dataReceivedListener.data(dataHex);
    }
}
