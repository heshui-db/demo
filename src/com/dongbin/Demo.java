package com.dongbin;


public class Demo {

    private Demo() {
        System.out.println("init");
    }

    private static void test() {
        System.out.println("test");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Demo.test();
    }
}
