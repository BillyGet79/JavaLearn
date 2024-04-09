package com.itheima.d2_params;

import java.util.Arrays;

public class MethodDemo {
    public static void main(String[] args) {
        sum();  //1、不传参数
        sum(10);    //2、可以传输一个参数
        sum(10, 20, 30);    //3、可以传输多个参数
        sum(new int[]{10, 20, 30, 40, 50}); //4、可以传输一个数组
    }


    /**
     * 注意：一个形参列表中只能由一个可变参数
     * @param nums
     */
    public static void sum(int...nums){
        //注意：可变参数在方法内部其实就是一个数组。
        System.out.println("元素个数：" + nums.length);
        System.out.println("元素内容" + Arrays.toString(nums));
    }
}
