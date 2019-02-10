package com.dongbin.algorithm.db20190210;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 数据过滤
 */
public class BloomFilter {

    //bit 域
    public static final int NUM_SLOTS = 1024 * 1024 * 8;
    //hash 函数的个数
    public static final int NUM_HASH = 8;

    private BigInteger bigMap = new BigInteger("0");


    public void push(String message) {
        for (int i = 0; i < NUM_HASH; i++) {
            int hashcode = hash(message, i);
            if (!bigMap.testBit(hashcode)) {
                bigMap = bigMap.or(new BigInteger("1")).shiftLeft(hashcode);
            }
        }
    }

    public boolean contains(String message) {
        for (int i = 0; i < NUM_HASH; i++) {
            int hashcode = hash(message, i);
            if (!bigMap.testBit(hashcode)) {
                return false;
            }
        }
        return true;
    }

    //哈希函数
    private int hash(String message, int n) {
        try {

            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            message = message + String.valueOf(n);
            messageDigest.update(message.getBytes());
            BigInteger bigInteger = new BigInteger(messageDigest.digest());
            return Math.abs(bigInteger.intValue()) % NUM_SLOTS;
        } catch (Exception e) {
            //do not thing
        }

        return -1;
    }
}
