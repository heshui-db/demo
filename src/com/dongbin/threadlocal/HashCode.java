package com.dongbin.threadlocal;

public class HashCode {

    public static void main(String[] args) {
        int HASH_INCREMENT = 0x61c88647;
        System.out.println(HASH_INCREMENT&15);
        System.out.println((HASH_INCREMENT*2)&15);
    }
}
