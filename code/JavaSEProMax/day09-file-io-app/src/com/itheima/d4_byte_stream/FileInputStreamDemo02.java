package com.itheima.d4_byte_stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 目标；使用文件字节输入流每次读取一个字节数组的数据
 */
public class FileInputStreamDemo02 {
    public static void main(String[] args) throws IOException {
        //1、创建于给文件字节输入流管道与源文件接通
        InputStream is = new FileInputStream("day9-file-io-app\\src\\resources\\data02.txt");

        //2、定义一个字节数组，用于读取数据
//        byte[] buffer = new byte[3];    //3B
//        int len = is.read(buffer);
//        System.out.println("读取了几个字节：" + len);
//        //读取多少倒出多少，注意这个写法
//        String rs = new String(buffer, 0, len);
//        System.out.println(rs);
//
//        int len1 = is.read(buffer);
//        System.out.println("读取了几个字节：" + len1);
//        String rs1 = new String(buffer, 0, len1);
//        System.out.println(rs1);
//
//        int len2 = is.read(buffer);
//        System.out.println("读取了几个字节：" + len2);
//        String rs2 = new String(buffer, 0, len2);
//        System.out.println(rs2);
//
//        int len3 = is.read(buffer);
//        System.out.println(len3);

        //3、改进使用循环，每次读取一个字节数组
        //如果遇到中文的话，该方法无法避免乱码问题
        byte[] buffer = new byte[3];
        int len;
        while ((len = is.read(buffer)) != -1) {
            System.out.print(new String(buffer, 0, len));
        }
    }
}
