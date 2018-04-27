package com.dongbin;

import com.sun.tools.javac.util.Assert;

/**
 * Created by dongbin on 2018/4/20.
 */
public class Demo {

    public static void main(String[] args) {
        boolean flag = false;
        Assert.check(flag);
        System.out.println(flag);
        throw new Error("");

    }
}
