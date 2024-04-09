package com.itheima.loop;

import java.util.Scanner;

public class DeadForDemo8 {
    public static void main(String[] args) {
        //目标：学会定义死循环。
//        for ( ; ; ) {
//            System.out.println("Hello World");
//        }

//        //经典写法
//        while (true){
//            System.out.println("我是快乐的死循环");
//        }

//        do {
//            System.out.println("Hello World");
//        }while (true);
        System.out.println("------------------------");
        //1、定义正确的密码
        int okpassword = 520;
        //2、定义一个死循环让用户不断地输入密码认证
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("请您输入正确的密码：");
            int password = sc.nextInt();
            //3、使用if判断密码是否正确
            if (password == okpassword){
                System.out.println("登录成功了~~~~~~");
                break;
            }else {
                System.out.println("密码错误！");
            }
        }
    }
}
