package com.itheima.create;

public class ArrayDemo5 {
    public static void main(String[] args) {
        //目标：掌握动态初始化元素默认值的规则
        //1、整型数组的元素默认值都是0
        int[] arr = new int[10];
        System.out.println(arr[0]);
        System.out.println(arr[9]);

        //2、字符数组的元素默认值是0
        char[] chars = new char[100];
        System.out.println((int)chars[0]);
        System.out.println((int)chars[99]);

        //3、浮点型整数的元素默认值是0.0
        double[] score = new double[90];
        System.out.println(score[0]);
        System.out.println(score[89]);

        //4、布尔类型的数组     默认为false
        boolean[] booleans = new boolean[100];
        System.out.println(booleans[0]);
        System.out.println(booleans[99]);

        //5、引用类型的数组     默认为null
        String[] names = new String[90];
        System.out.println(names[0]);
        System.out.println(names[89]);

    }
}
