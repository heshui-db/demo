package com.dongbin.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by dongbin on 2018/3/9.
 */
public class AOPHandler implements InvocationHandler {

    private Object target;

    public AOPHandler(Object target) {
        this.target = target;
    }

    public void println(String str, Object... args) {
        System.out.println(str);
        if (args == null) {
            System.out.println("args is null");
        } else {
            for (Object arg : args) {
                System.out.println(arg);
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用方法：" + method.getName());
        Class<?>[] variables = method.getParameterTypes();
        System.out.println("传入参数类型：");
        for (Class<?> var : variables) {
            System.out.println("\t\t\t" + var.getName());
        }

        System.out.println("传入参数值：");
        for (Object object : args) {
            System.out.println(object);
        }

        Object result = method.invoke(target, args);
        println("返回参数", result);
        println("返回参数类型", method.getReturnType());
        return result;
    }
}
