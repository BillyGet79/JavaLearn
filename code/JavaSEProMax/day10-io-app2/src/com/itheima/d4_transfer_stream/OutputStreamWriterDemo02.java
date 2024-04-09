package com.itheima.d4_transfer_stream;

import java.io.*;

public class OutputStreamWriterDemo02 {
    public static void main(String[] args) {
        try (
                //定义一个字节输出流
                OutputStream os = new FileOutputStream("day10-io-app2\\src\\resources\\out03.txt");

                //把原始的字节输出流转换成字符输出流
                Writer osw = new OutputStreamWriter(os, "GBK");    //指定GBK的方式写字符出去

                BufferedWriter bw = new BufferedWriter(osw);
                ) {
            bw.write("我爱中国1~~");
            bw.newLine();
            bw.write("我爱中国2~~");
            bw.newLine();
            bw.write("我爱中国3~~");
            bw.newLine();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
