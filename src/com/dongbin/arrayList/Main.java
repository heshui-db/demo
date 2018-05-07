package com.dongbin.arrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Integer[] integers1 = new Integer[3];

        list.toArray(integers1);

        System.out.println(integers1);

        Integer[] integers2 = list.toArray(new Integer[0]);


        //比较的是数组integers1 和 integers2 里的内容是否相等
        System.out.println(Arrays.equals(integers1,integers2));


        Integer[] integers3 = {1,2,3};
        List<Integer> list1 = Arrays.asList(integers3);
        //list1.add(4);// 没有重写add方法，有abstractList 实现抛异常UnsupportedOperationException

        List<Integer> list2 = new ArrayList<>(Arrays.asList(integers3));

        list2.add(4);





    }
}
