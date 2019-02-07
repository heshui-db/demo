package com.dongbin.DB_2018_06_04;

import com.dongbin.cache.Account;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class clazz = classLoader.loadClass("com.dongbin.cache.Account");

        Constructor constructor = clazz.getConstructor(String.class);
        Account instance = (Account) constructor.newInstance("dongbin");

//        Field[] fields = clazz.getFields();
//        for (Field field : fields) {
//            field.set(field.getType(),"");
//        }


        Method method1 = clazz.getMethod("setId", int.class);
        method1.invoke(instance, 10);
        Method method2 = clazz.getMethod("setName", String.class);
        method2.invoke(instance, "dongbin1");
        Field field = clazz.getDeclaredField("id");
        field.setAccessible(true);
        field.set(instance, 20);
        System.out.println(instance.getId() + ":" + instance.getName());
    }

    /**
     * ClassLoader 分为根类加载器、ExtClassLoader、APPClassLoader
     */
    public static void testLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }
}
