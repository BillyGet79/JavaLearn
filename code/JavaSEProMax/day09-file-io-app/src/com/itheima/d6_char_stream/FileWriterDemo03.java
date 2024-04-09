package com.itheima.d6_char_stream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileWriterDemo03 {
    public static void main(String[] args) throws Exception {
        //1、创建一个字符输出流管道与目标文件接通
        //Writer fw = new FileWriter("day9-file-io-app\\src\\resources\\out08.txt");  //覆盖管道，每次启动都会清空文件之前的数据
        Writer fw = new FileWriter("day9-file-io-app\\src\\resources\\out08.txt", true);  //覆盖管道，每次启动都会清空文件之前的数据

        //写一个字符出去
        fw.write(98);
        fw.write('a');
        fw.write('徐');  //不会出问题了
        fw.write("\r\n");

        //写一个字符串出去
        fw.write("abc我是中国人");
        fw.write("\r\n");

        //写一个字符数组出去
        char[] chars = "abc我是中国人".toCharArray();
        fw.write(chars);
        fw.write("\r\n");

        //写字符串的一部分出去
        fw.write("abc我是中国人", 0, 5);
        fw.write("\r\n");

        //写字符数组的一部分出去
        fw.write(chars, 3, 5);
        fw.write("\r\n");

        //fw.flush(); //刷新后流可以继续使用
        fw.close(); //关闭包含刷新，关闭后流不能使用
    }
}
