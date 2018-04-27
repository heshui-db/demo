package com.dongbin.bitset;

import java.util.BitSet;
import java.util.Random;

/**
 * Created by dongbin on 2018/4/25.
 */
public class Demo {


    public static void main(String[] args) {
        System.out.println(0xffffffffffffffffL>>> 63);
    }

    public static void method1() {
        Random random = new Random();
        BitSet bitSet = new BitSet(64);
        for (int i = 0; i < 30; i++) {
            int tmp = random.nextInt(64);
            bitSet.set(tmp);
            System.out.print(tmp + "\t");
        }
        System.out.println();
        for (int i = 1; i <= 64; i++) {
            if (!bitSet.get(i)) {
                System.out.print(i + "\t");
            }
        }
    }

    public void method2() {
        BitSet bitSet = new BitSet(Integer.MAX_VALUE);

        String str = "";

        if(bitSet.get(str.hashCode()) && bitSet.get(("xxx-"+str).hashCode())){
            System.out.println("存在");
        }

        //在插入的时候要set 2个值 一个是str的hashcode 另一个是"xxx-"+str 的hashcode

    }


}
