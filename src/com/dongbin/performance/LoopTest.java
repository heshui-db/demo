package com.dongbin.performance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoopTest {

    public static void main(String[] args) {
        Map<String,List<String>> listMap = new HashMap<>();
        listMap.put("B",new ArrayList<>());

        System.out.println(listMap.get("A"));
        System.out.println(listMap.get("B"));

    }

}
