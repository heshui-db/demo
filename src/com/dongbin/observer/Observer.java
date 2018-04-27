package com.dongbin.observer;

/**
 * Created by dongbin on 2017/9/25.
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
