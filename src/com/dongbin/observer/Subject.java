package com.dongbin.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongbin on 2017/9/25.
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;
    private boolean changed;

    private final Object MUTEX = new Object();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        this.changed = true;
        notifyAllObservers();
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    public void unRegister(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers() {
        List<Observer> observersLocal = null;
        synchronized (MUTEX) {
            if (!changed) return;
            observersLocal = new ArrayList<>(this.observers);
        }
        for (Observer observer : observersLocal) {
            observer.update();
        }
    }
}
