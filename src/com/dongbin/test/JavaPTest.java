package com.dongbin.test;

/**
 * Created by dongbin on 2018/1/14.
 */
public class JavaPTest {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        JavaPTest test = BeanFactory.getBean("com.dongbin.test.JavaPTest",JavaPTest.class);
        test.add("dong","bin");
    }

    public void add(String a, String b) {
        String c = a + b;
        System.out.println(c);

    }


}
