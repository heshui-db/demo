package com.dongbin.observer;

/**
 * Created by dongbin on 2017/9/25.
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject){
        this.subject = subject;
        //this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("This is BinaryObserver,state:"+subject.getState());
    }
}
