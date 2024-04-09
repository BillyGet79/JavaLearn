package com.itheima.operator;

public class OperatorDemo8 {
    public static void main(String[] args) {
        //目标：学会使用三元运算符，理解其流程
        double score = 98;
        String rs = score >= 60 ? "考试通过" : "挂科";
        System.out.println(rs);

        //需求：需要从两个整数中找出较大值
        int a = 10000;
        int b = 2000;
        int max = a >= b ? a : b;
        System.out.println(max);

        System.out.println("------------------------");
        int i = 10;
        int j = 30;
        int k = 50;
        //1、找出两个整数的较大值
        int temp = i > j ? i : j;
        //2、拿临时变量与第三个变量的值继续比较
        int reMax = temp > k ? temp : k;
        System.out.println(reMax);

        //也可以写成这样
        int temp2 = (i > j ? i : j) > k ? (i > j ? i : j) : k;
        System.out.println(temp2);
    }
}
