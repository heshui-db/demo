package com.dongbin.observer;

/**
 * Created by dongbin on 2017/9/25.
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.register(this);
    }

    @Override
    public void update() {
        System.out.println("This is OctalObserver,state:"+subject.getState());
    }
}
