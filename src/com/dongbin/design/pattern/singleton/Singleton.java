package com.dongbin.design.pattern.singleton;

/**
 * 单例模式
 * <p>
 * 饿汉式 缺点没有懒加载
 */
public class Singleton {

    private final static Singleton SINGLETON = new Singleton();

    public static Singleton getSingleton() {
        return SINGLETON;
    }

    private Singleton() {
    }
}


/**
 * 懒汉式
 * <p>
 * 性能低
 */
class Singleton1 {

    private static Singleton1 SINGLETON;


    private Singleton1() {
    }

    public static synchronized Singleton1 getSingleton() {
        if (SINGLETON == null) {
            SINGLETON = new Singleton1();
        }

        return SINGLETON;
    }

}

/**
 * 静态内部类的方式
 * <p>
 * 推荐使用这种
 */
class Singleton2 {

    private Singleton2() {
    }

    private static class SingleBuilder {
        private final static Singleton2 SINGLETON = new Singleton2();
    }

    public static Singleton2 getSingleton() {
        return SingleBuilder.SINGLETON;
    }
}

class Singleton3 {
    public static class Singleton3Bulider {
        private final static Singleton3 singleton3 = new Singleton3();
    }

    public static Singleton3 getSingleton() {
        return Singleton3Bulider.singleton3;
    }
}
