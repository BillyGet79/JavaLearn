package com.itheima.d3_char_buffer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

/**
 * 目标：缓冲字符输出流的使用，学会它多出来的功能
 */
public class BufferedWriterDemo02 {
    public static void main(String[] args) throws Exception {
        //1、创建一个字符输出流管道与目标文件接通
        //Writer bw = new FileWriter("day9-file-io-app\\src\\resources\\out08.txt");  //覆盖管道，每次启动都会清空文件之前的数据
        Writer fw = new FileWriter("day10-io-app2\\src\\resources\\out01.txt");  //覆盖管道，每次启动都会清空文件之前的数据
        BufferedWriter bw = new BufferedWriter(fw);

        //写一个字符出去
        bw.write(98);
        bw.write('a');
        bw.write('徐');  //不会出问题了
        bw.newLine();   //使用这个方法换行性能会更好

        //写一个字符串出去
        bw.write("abc我是中国人");
        bw.newLine();

        //写一个字符数组出去
        char[] chars = "abc我是中国人".toCharArray();
        bw.write(chars);
        bw.newLine();

        //写字符串的一部分出去
        bw.write("abc我是中国人", 0, 5);
        bw.newLine();

        //写字符数组的一部分出去
        bw.write(chars, 3, 5);
        bw.newLine();

        //bw.flush(); //刷新后流可以继续使用
        bw.close(); //关闭包含刷新，关闭后流不能使用
    }
}
