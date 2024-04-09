package com.itheima.d5_serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

/**
 * 目标：学会进行对象反序列化
 */
public class ObjectInputStreamDemo2 {
    public static void main(String[] args) throws Exception {
        //1、创建对象字节输入流管道包装低级的字节输入流管道
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("day10-io-app2\\src\\resources\\obj.txt"));

        //2、对象反序列化
        Student s = (Student) ois.readObject();

        System.out.println(s);
    }
}
