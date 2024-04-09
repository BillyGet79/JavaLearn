package com.itheima.d4_transfer_stream;

import java.io.*;

public class InputStreamReaderDemo01 {
    public static void main(String[] args) {
        //代码UTF-8   文件UTF-8
        try (
                //提取GBK文件的原始字节流
                InputStream is = new FileInputStream("day10-io-app2\\src\\resources\\new01.txt");
                //把原始字节流转换成字符输入流
                Reader isr = new InputStreamReader(is, "GBK"); //以指定的GBK编码转换成字符输入流  完美解决代码问题

                BufferedReader br = new BufferedReader(isr);
                ) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
