package com.dongbin;


import java.util.List;

public class Demo {

    static class A {

        private int value;

        A(int value) {
            this.value = value;
        }

    }

    static class B extends A {

        public B(int value) {
            super(value);
        }
    }

    static class C extends B {

        public C(int value) {
            super(value);
        }
    }

    public static void main(String[] args) {
        System.out.println(-1/2);

    }

    private static <T> void copy1(List<? super T> dest, List<? extends T> src) {
        dest.add(src.get(0));

        System.out.println(dest);
    }

    private static <T> void copy(List<T> dest, List<? extends T> src) {
        dest.add(src.get(0));
        System.out.println(dest);
    }
}
