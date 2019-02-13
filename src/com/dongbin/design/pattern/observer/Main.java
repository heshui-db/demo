package com.dongbin.design.pattern.observer;

public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer observer1 = new ObserverImpl("观察者1");
        Observer observer2 = new ObserverImpl("观察者2");

        subject.registry(observer1);
        subject.change("nihao");
        subject.registry(observer2);
        subject.change("mingtianhuigenghoa");
        subject.unRegistry(observer1);
        subject.change("ssssss");
    }
}
