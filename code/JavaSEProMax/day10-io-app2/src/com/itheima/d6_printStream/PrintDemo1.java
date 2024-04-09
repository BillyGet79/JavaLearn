package com.itheima.d6_printStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 目标：学会使用打印流
 */
public class PrintDemo1 {
    public static void main(String[] args) throws Exception {
        //1、创建一个打印流对象
        //PrintStream ps = new PrintStream(new FileOutputStream("day10-io-app2\\src\\resources\\ps.txt"));
        PrintStream ps = new PrintStream("day10-io-app2\\src\\resources\\ps.txt");

        ps.println(97);
        ps.println('a');
        ps.println(23.3);
        ps.println(true);
        ps.println("我是打印输出流的");

        ps.close();
    }
}
