package com.itheima.d7_properties;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo02 {
    public static void main(String[] args) throws Exception {
        //需求：使用Properties读取属性文件中的键值对信息
        Properties properties = new Properties();
        System.out.println(properties);

        //加载属性文件中的键值对数据到属性对象properties中去
        properties.load(new FileReader("day10-io-app2\\src\\resources\\users.properties"));

        System.out.println(properties);

        String rs = properties.getProperty("dlei");
        System.out.println(rs);
        String rs1 = properties.getProperty("admin");
        System.out.println(rs1);

        
    }
}
