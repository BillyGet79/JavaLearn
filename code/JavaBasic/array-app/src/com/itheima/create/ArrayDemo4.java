package com.itheima.create;

public class ArrayDemo4 {
    public static void main(String[] args) {
        //目标：学会动态初始化数组的定义和使用
        double[] score = new double[3]; //[0.0, 0.0, 0.0]

        //赋值
        score[0] = 99.5;
        System.out.println(score[0]);

        System.out.println(score[2]);   //未赋值默认为0.0

        String[] names = new String[90];
        names[0] = "迪丽热巴";
        names[2] = "马尔扎哈";
        System.out.println(names[0]);
        System.out.println(names[1]);   //未赋值默认为null
        System.out.println(names[2]);

    }
}
