package com.itheima.demo;

public class Test3 {
    public static void main(String[] args) {
        //需求：使用方法，支持找出任意整型数组的最大值返回。
        int[] ages = {23, 19, 25, 78, 34};
        System.out.println("最大值数据是：" + getArrayMaxDate(ages));
    }

    public static int getArrayMaxDate(int[] arr){
        //找出数组的最大值返回
        int max = arr[0];
        //遍历数组的每个元素与最大值的数据进行比较
        for (int i = 1; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        return max;
    }
}
