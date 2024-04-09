package com.itheima.demo;

public class Test2 {
    public static void main(String[] args) {
        //需求：数组元素求最值
        //1、定义一个静态初始化的数组，存储一批颜值
        int[] facsScore = {15, 9000, 10000, 20000, 9500, -5};

        //2、定义一个变量用于存储最大值元素，建议使用第一个元素作为参照
        int max = facsScore[0];

        //3、遍历数组的每个元素，依次与最大值变量的数据比较，若较大，则替换
        for (int i = 1; i < facsScore.length; i++) {
            max = facsScore[i] > max ? facsScore[i] : max;
        }

        //4、输出最大值变量存储的数据即可
        System.out.println("数组的最大值是：" + max);
    }
}
