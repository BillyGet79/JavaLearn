package com.itheima.string;

import java.util.Scanner;

/**
 * 练习题：手机号码屏蔽
 */
public class StringExec6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请您输入您的手机号码：");
        String numberphone = sc.next();
        String code = numberphone.replace(numberphone.substring(3, 7), "****");
        System.out.println(code);
    }
}
