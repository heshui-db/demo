package com.dongbin.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        A a = null;
        if (a instanceof A){
            System.out.println("sss");
        }

//        Field[] declaredFields = B.class.getDeclaredFields();
//        Arrays.asList(declaredFields).forEach(field -> System.out.print(field.getName()));
//        System.out.println();
//        Field[] fields = B.class.getFields();
//        Arrays.asList(fields).forEach(field -> System.out.print(field.getName()));
//        System.out.println();
//        List<Field> allFields = listAllFields(B.class);
//        allFields.forEach(field -> System.out.print(field.getName()));
    }

    private static List<Field> listAllFields(Class clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
