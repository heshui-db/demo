package com.dongbin.hashmap;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("AD:AD");
        list.add("AD:AD+BD");
        list.add("AD:AD+AP:AD");
        list.add("AD:ADA+QPP");
        list.add("5");
        list.add("6");
        list.sort(String::compareTo);
        System.out.println(list);
    }
}
