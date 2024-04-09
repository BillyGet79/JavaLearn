package com.itheima.d4_byte_stream;

import java.io.*;

/**
 *
 */
public class FileInputStreamDemo01 {
    public static void main(String[] args) throws IOException {
        //1、创建一个文件字节输入流管道与源文件接通
        InputStream is = null;
        try {
            //完整写法
            //is = new FileInputStream(new File("day9-file-io-app\\src\\resources\\data.txt"));
            //简化写法，就是不用新建一个文件类
            is = new FileInputStream("day9-file-io-app\\src\\resources\\data.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //2、读取一个字节返回
//        int b1 = is.read();
//        System.out.println((char) b1);
//
//        int b2 = is.read();
//        System.out.println((char) b2);
//
//        int b3 = is.read();
//        System.out.println((char) b3);
//
//        //由于中文占三个字节，所以在读取三个字节之后，文件读取完毕，最后返回-1的结果
//        int b4 = is.read();
//        System.out.println(b4);
//
//        int b5 = is.read();
//        System.out.println(b5);
//
//        int b6 = is.read();
//        System.out.println(b6);
//
//        int b7 = is.read();
//        System.out.println(b7);

        //3、使用循环改进
        //定义一个变量记录每次读取的字节
        int b;
        while ((b = is.read()) != -1) {
            System.out.print((char) b);
        }
    }
}
