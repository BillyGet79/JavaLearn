package com.itheima.d1_file;

import java.io.File;

/**
 * 目标：学会创建File对象定位操作系统的文件（文件、文件夹）
 */
public class FileDemo {
    public static void main(String[] args) {
        //1、创建File对象(指定了文件的路径)
        //路径写法：双反斜杠可以换成单正斜杠，也可以使用方法File.separator来代替（不过这个方法一般情况下不用）
        //File f = new File("D:\\JavaLearn\\code\\JavaSEProMax\\day9-file-io-app\\resources\\data.txt");
        File f = new File("D:/JavaLearn/code/JavaSEProMax/day9-file-io-app/src/resources/data.txt");
        long size = f.length(); //文件的字节大小
        System.out.println(size);

        //2、File常见对象：文件绝对路径 文件相对路径
        File f1 = new File("D:\\JavaLearn\\code\\JavaSEProMax\\day9-file-io-app\\src\\resources\\data.txt"); //绝对路径
        System.out.println(f1.length());

        //相对路径：一般定为模块中的文件的  相对到工程下
        File f2 = new File("day9-file-io-app/src/resources/data.txt");
        System.out.println(f2.length());

        //3、File创建对象，可以是文件也可以是文件夹
        File f3 = new File("day9-file-io-app/src/resources");
        System.out.println(f3.exists());    //判断这个路径是否存在，这个文件夹是否存在
    }
}
