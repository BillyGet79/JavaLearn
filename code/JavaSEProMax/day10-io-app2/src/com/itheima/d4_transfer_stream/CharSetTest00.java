package com.itheima.d4_transfer_stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * 演示一下代码编码与文件编码相同和不同的情况
 */
public class CharSetTest00 {
    public static void main(String[] args) {
        try (
                //代码：UTF-8  文件UTF-8
                //创建一个字符输入流管道与源文件接通
                //Reader fr = new FileReader("D:\\JavaLearn\\code\\JavaSEProMax\\day09-file-io-app\\src\\resources\\data07.txt");

                //代码UTF-8  文件GBK    乱码
                Reader fr = new FileReader("day10-io-app2\\src\\resources\\new01.txt");
                //把低级的字符输入流包装成高级的缓冲字符输入流
                BufferedReader br = new BufferedReader(fr);
        ) {

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
