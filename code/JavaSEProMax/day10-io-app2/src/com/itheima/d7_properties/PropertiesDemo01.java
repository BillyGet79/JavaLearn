package com.itheima.d7_properties;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertiesDemo01 {
    public static void main(String[] args) throws IOException {
        //需求：使用Properties把键值对信息存入到属性文件中去
        //这个方法与put方法一样
        Properties properties = new Properties();
        properties.setProperty("admin", "123456");
        properties.setProperty("dlei", "003197");
        properties.setProperty("heima", "itcast");
        System.out.println(properties);

        /**
         * 参数一：保持村官到    字符输出流管道
         * 参数二：保存心得
         */
        properties.store(new FileWriter("day10-io-app2\\src\\resources\\users.properties"), "this is users!! i am very happy! give me 100!");


    }
}
