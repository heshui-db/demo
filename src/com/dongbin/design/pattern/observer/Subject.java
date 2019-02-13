package com.dongbin.design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<>();

    public void registry(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unRegistry(Observer observer) {
        observers.remove(observer);
    }

    public void change(String msg) {
        for (Observer observer : observers) {
            observer.accept(msg);
        }
    }
}
