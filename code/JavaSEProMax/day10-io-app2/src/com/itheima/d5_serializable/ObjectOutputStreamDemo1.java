package com.itheima.d5_serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 目标：学会对象序列化
 */
public class ObjectOutputStreamDemo1 {
    public static void main(String[] args) throws IOException {
        //1、创建学生对象
        Student s = new Student("陈磊","chenlei","1314520", 21);

        //2、对象序列化，使用对象字节输出流包装字节输出流管道
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("day10-io-app2\\src\\resources\\obj.txt"));

        //3、直接调用序列化方法
        oos.writeObject(s);

        //4、释放资源
        oos.close();
        System.out.println("序列化完成了~~~");
    }
}
