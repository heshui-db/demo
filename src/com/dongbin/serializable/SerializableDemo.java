package com.dongbin.serializable;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by dongbin on 2018/3/21.
 */
public class SerializableDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        FileOutputStream fos = new FileOutputStream("/opt/fit2cloud/test.txt");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//        Person person = new Person();
//        person.setAge(18);
//        person.setName("dongbin");
//        person.setSex("man");
//
//        oos.writeObject(person);
//        oos.flush();
//        oos.close();
//
//        FileInputStream fis = new FileInputStream("/opt/fit2cloud/test.txt");
//        FileInputStream fis2 = new FileInputStream("/opt/fit2cloud/test.txt");
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        ObjectInputStream ois2 = new ObjectInputStream(fis2);
//        Person object1 = (Person) ois.readObject();
//        Person object2 = (Person) ois2.readObject();
//        System.out.println(object1 == object2);
        People people = new People();
        int[][] array = {{1, 2, 8, 9}, {4, 7, 10, 13}};
        people.Find(7, array);

    }
}

class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private static int a = 5;

    private String name;
    private String sex;
    private int age;

    private int aa;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class People {

    private String var1;
    private String var2;

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public boolean Find(int target, int[][] array) {


        int i = 0;
        int m = -1;
        while (i < array.length) {
            int k = m == -1 ? array[i].length : m, j = 0;
            while (j < k) {
                int mid = (j + k - 1) / 2;
                if (array[i][mid] > target) {
                    k = mid;
                } else if (array[i][mid] < target) {
                    j = mid + 1;
                } else {
                    return true;
                }
            }
            m = k;
            if (m == 0) {
                return false;
            }
            i++;
        }
        return false;
    }

    ;

}
