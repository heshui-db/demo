package com.dongbin.swap;

/**
 * Created by dongbin on 2018/4/27.
 */

/**
 * 这里写 异或交换数据
 */
public class Demo {

    public static void main(String[] args) {
        int a=10,b=10;
        swap(a,b);
    }

    public static void swap(int a, int b) {
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println(a+":"+b);
    }
}
