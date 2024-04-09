package com.itheima.d3_char_buffer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 目标：学会使用缓冲字符输入流提高字符输入流的性能，新增了按照行读取的方法(经典代码)
 */
public class BufferedReaderDemo1 {
    public static void main(String[] args) {
        try (
                //创建一个字符输入流管道与源文件接通
                Reader fr = new FileReader("D:\\JavaLearn\\code\\JavaSEProMax\\day09-file-io-app\\src\\resources\\data07.txt");
                //把低级的字符输入流包装成高级的缓冲字符输入流
                BufferedReader br = new BufferedReader(fr);
                ) {


            //用循环，每次读取一个字符数组的数据
//            char[] buffer = new char[1024]; //1K字符
//            int len;
//            while ((len = br.read(buffer)) != -1) {
//                String rs = new String(buffer, 0, len);
//                System.out.println(rs);
//            }

            //使用readline()方法一行一行读完
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
