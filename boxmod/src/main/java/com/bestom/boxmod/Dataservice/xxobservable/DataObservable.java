package com.bestom.boxmod.Dataservice.xxobservable;

import com.bestom.boxmod.interfa.service.Observable;
import com.bestom.boxmod.interfa.service.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * xxData被观察者，串口数据中转 通知服务
 */
public class DataObservable implements Observable {

    //关键字volatile:用以声明变量的值可能随时会别的线程修改，
    // 使用volatile修饰的变量会强制将修改的值立即写入主存，
    // 主存中值的更新会使缓存中的值失效(非volatile变量不具备这样的特性，
    // 非volatile变量的值会被缓存，线程A更新了这个值，
    // 线程B读取这个变量的值时可能读到的并不是是线程A更新后的值)。
    // volatile会禁止指令重排。
    private static volatile DataObservable instance;

    private List<Observer> observerList;
    private String dataHex;

    public DataObservable() {
        observerList=new ArrayList<Observer>();
    }

    public static  DataObservable getInstance(){
     if (instance==null){
     //关键字synchronized:一个线程访问一个对象中的synchronized(this)同步代码块时，
     // 其他试图访问该对象的线程将被阻塞
     //该代码块只会同步执行,不会异步在不同线程
     synchronized (DataObservable.class){
     if (instance==null){
     instance=new DataObservable();
     }
     }
     }
     return instance;
     }

     /**
     *注册观察者
     * @param o 观察者
     */
    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    /**
     * 移除观察者
     * @param o
     */
    @Override
    public void removeObserver(Observer o) {
        if (!observerList.isEmpty()){
            observerList.remove(o);
        }
    }

    /**
     * 通知观察者更新
     */
    @Override
    public void notifyObserver() {
        for (int i=0;i<observerList.size();i++){
            Observer observer=observerList.get(i);
            observer.dataReceived(dataHex);
        }
    }

    /**
     * 观察者更新的新信息
     * @param sHex  16进制字符串
     */
    public void setInfo(String sHex){
        this.dataHex=sHex;
        notifyObserver();
    }
}