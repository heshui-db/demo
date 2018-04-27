package com.dongbin.observer;

/**
 * Created by dongbin on 2017/9/25.
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);

        System.out.println("set state is 10");
        subject.setState(10);

        System.out.println("set state is 17");
        subject.setState(17);

    }
}
