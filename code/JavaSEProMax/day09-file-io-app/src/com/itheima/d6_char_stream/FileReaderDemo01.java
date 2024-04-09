package com.itheima.d6_char_stream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * 目标：字符输入流的使用
 */
public class FileReaderDemo01 {
    public static void main(String[] args) throws Exception {
        //目标：每次读取一个字符
        //创建一个字符输入流管道与源文件接通
        Reader fr = new FileReader("day9-file-io-app\\src\\resources\\data06.txt");

        //读取一个字符，没有可读的字符了返回-1
//        int code = fr.read();
//        System.out.print((char) code);
//        int code1 = fr.read();
//        System.out.print((char) code1);

        //使用循环定义字符
        int code;
        while ((code = fr.read()) != -1) {
            System.out.print((char) code);
        }

        fr.close();
    }
}
