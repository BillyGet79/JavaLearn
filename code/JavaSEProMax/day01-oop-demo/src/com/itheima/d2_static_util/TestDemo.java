package com.itheima.d2_static_util;

import java.util.ArrayList;

public class TestDemo {
    public static void main(String[] args) {
        //测试ArrayUtils工具类
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Double> arr2 = new ArrayList<>();
        arr1.add(10);
        arr1.add(20);
        arr1.add(50);
        arr1.add(34);
        arr1.add(100);
        arr2.add(98.5);
        arr2.add(98.4);
        arr2.add(98.3);
        arr2.add(98.2);
        arr2.add(98.1);

        String arr1Answer = ArrayUtils.toString(arr1);
        Double average = ArrayUtils.getAverage(arr2);
        System.out.println(arr1Answer);
        System.out.println(average);
    }
}
