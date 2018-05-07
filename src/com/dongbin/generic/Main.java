package com.dongbin.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer>  list = new ArrayList<>();
        list.add(100);
        List<? super Number> numbers = new ArrayList<>();
        numbers.add(100);
        Object o = numbers.get(0);
        System.out.println(numbers.size());

        Type type = new ArrayList<String>(){}.getClass().getGenericSuperclass();
        Type type1 = ((ParameterizedType)type).getActualTypeArguments()[0];


        System.out.println(type1.getTypeName());
    }

    public static void put(List<? super Number> numbers){
        for(int i=0;i<10;i++){
            numbers.add(i);
        }
    }
}
