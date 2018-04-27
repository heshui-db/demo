package com.dongbin.test;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by dongbin on 2018/3/9.
 */
public class BeanFactory {

    public static Object getBean(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object object = Class.forName(className).newInstance();
        InvocationHandler handler = new AOPHandler(object);
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), handler);
    }

    public static <T> T getBean(String className, Class<T> clazz) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return (T)getBean(className);
    }
}
