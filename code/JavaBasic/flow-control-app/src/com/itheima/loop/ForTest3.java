package com.itheima.loop;

public class ForTest3 {
    public static void main(String[] args) {
        //需求：求1-10的奇数和
        //方法一：
        int sum = 0;
        for (int i = 1; i <= 10 ; i++) {
            if(i % 2 != 0){
                sum += i;
            }
        }
        System.out.println("1-10的奇数和是：" + sum + "（方法一）");
        //方法二
        sum = 0;
        for (int i = 1; i <= 10; i += 2) {
            sum += i;
        }
        System.out.println("1-10的奇数和是：" + sum + "（方法二）");
    }
}
