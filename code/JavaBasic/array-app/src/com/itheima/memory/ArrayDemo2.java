package com.itheima.memory;

public class ArrayDemo2 {
    public static void main(String[] args) {
        //目标：理解2个数组变量指向同一个数组对象
        int[] arr1 = {11, 22, 33};

        //把数组arr1变量赋值给int类型的数组变量arr2
        int[] arr2 = arr1;

        System.out.println(arr1);
        System.out.println(arr2);

        arr1[1] = 99;
        System.out.println(arr1[1]);

        System.out.println(arr2[0]);
        System.out.println(arr2[1]);
        System.out.println(arr2[2]);
    }
}
