package com.dongbin.test;

/**
 * Created by dongbin on 2018/3/7.
 */
public class RetryTest {

    public static void main(String[] args) {
        int count = 0;
        retry:
        for (; ; ) {
            for (; ; ) {
                count++;
                System.out.println("count==" + count);
                if (count % 5 == 0) {
                    continue retry;
                }
            }
        }
    }
}
