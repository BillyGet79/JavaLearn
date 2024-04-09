package com.itheima.loop;

public class FotTest2 {
    public static void main(String[] args) {
        //需求：计算1-5的和
        //1、定义一个for循环找到1 2 3 4 5
        //2、定义一个数据变量用于累加数据求和
        int sum = 0;
        for (int i = 1; i <= 5; i++) {
            //3、把循环的数据累加给sum变量
            sum += i;
        }
        System.out.println("1-5的和是：" + sum);
    }
}
