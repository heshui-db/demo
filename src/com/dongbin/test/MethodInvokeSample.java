package com.dongbin.test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

/**
 * Created by dongbin on 2018/3/9.
 */
public class MethodInvokeSample {

    public static String test(String a, String b) {
        return a + b;
    }

    public static void main(String[] args) throws Exception {
//        Method method = MethodInvokeSample.class.getDeclaredMethod("test", String.class, String.class);
//        String result = (String) method.invoke(null, "dong", "bin");
//        System.out.println(result);

        Field[] fields = Node.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType()+":"+field.getName());

            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),Node.class);
            System.out.println(propertyDescriptor.getDisplayName());
            System.out.println(propertyDescriptor.getShortDescription());
            System.out.println(propertyDescriptor.getPropertyEditorClass());
            System.out.println(propertyDescriptor.getPropertyType());
            System.out.println(propertyDescriptor.getReadMethod());
            System.out.println(propertyDescriptor.getWriteMethod());
        }
    }

    static class Node {
        private String name;
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}

