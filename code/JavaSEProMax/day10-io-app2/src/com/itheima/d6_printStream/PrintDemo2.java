package com.itheima.d6_printStream;

import java.io.PrintStream;

/**
 * 目标：了解改变输出语句的位置到文件
 */
public class PrintDemo2 {
    public static void main(String[] args) throws Exception {
        System.out.println("锦瑟无端五十弦");
        System.out.println("一线一柱思华年");

        //改变输出语句的位置（重定向）
        PrintStream ps = new PrintStream("day10-io-app2\\src\\resources\\log.txt");
        System.setOut(ps);  //把系统打印流改成我们自己的打印流

        System.out.println("庄生晓梦迷蝴蝶");
        System.out.println("望帝春心托杜鹃");
    }
}
