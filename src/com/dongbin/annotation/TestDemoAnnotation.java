package com.dongbin.annotation;

import java.lang.reflect.Method;

public class TestDemoAnnotation {


    @Demo
    public void test() {
        System.out.println("test");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        TestDemoAnnotation testDemoAnnotation = new TestDemoAnnotation();
        testDemoAnnotation.test();
        Method method = testDemoAnnotation.getClass().getMethod("test");
    }
}
