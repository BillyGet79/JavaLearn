package com.itheima.traverse;

public class ArrayDemo {
    public static void main(String[] args) {
        //目标：学会进行数组元素的遍历
        int[] arr = {12, 24, 12, 48, 98};

        //原始遍历方式
        System.out.print(arr[0] + " ");
        System.out.print(arr[1] + " ");
        System.out.print(arr[2] + " ");
        System.out.print(arr[3] + " ");
        System.out.print(arr[4] + " ");
        System.out.println();

        //终极遍历方式
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        //快捷键   arr.fori
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
