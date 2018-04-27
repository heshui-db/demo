package com.dongbin.test;

/**
 * Created by dongbin on 2018/4/13.
 */
public class Demo {

    public static void reOrderArray(int [] array) {
        int[] newArr = new int[array.length];
        int k=0;
        for(int i=0;i<array.length;i++){
            if(array[i]%2==1){
                newArr[k++] = array[i];
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i]%2==0){
                newArr[k++] = array[i];
            }
        }

        for(int i=0;i<newArr.length;i++){
            array[i] = newArr[i];
        }

    }

    public static void main(String[] args) {
        int[] a= {1,2,3,4,5,6,7};
        reOrderArray(a);
        System.out.println(a);
    }
}
