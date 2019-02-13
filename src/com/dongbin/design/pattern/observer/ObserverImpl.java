package com.dongbin.design.pattern.observer;

public class ObserverImpl implements com.dongbin.design.pattern.observer.Observer {

    //观察者名称
    private String name;


    public ObserverImpl(String name) {
        this.name = name;
    }

    @Override
    public void accept(String msg) {
        System.out.println(this.name + "接收到消息：" + msg);
    }
}
