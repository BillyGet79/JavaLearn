package com.itheima.random;

import java.util.Random;
import java.util.Scanner;

public class RandomTest2 {
    public static void main(String[] args) {
        //需求：随机生成一个1-100之间的数据，提示用户猜测，猜大提示过大
        //猜小提示过小，直到猜中结束游戏
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int number = r.nextInt(100)+1;
        while (true){
            System.out.println("请输入您猜的数字：");
            int temp = sc.nextInt();
            if (temp == number){
                System.out.println("猜对了！答案是：" + number);
                break;
            }else if (temp > number){
                System.out.println("猜大了！");
            }else {
                System.out.println("猜小了！");
            }
        }
    }
}
