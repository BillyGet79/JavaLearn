package com.itheima.d1_file;

import java.io.File;
import java.io.IOException;

/**
 * 目标：File类的创建和删除的方法
 */
public class FileDemo03 {
    public static void main(String[] args) {
        File f = new File("day9-file-io-app/src/resources/data.txt");
        //a.创建新文件，创建成功返回true，反之，不需要这个，以后文件写出去的时候都会自动创建
        //不知道在jdk什么版本开始，createNewFile()方法必须使用try/catch包裹才能执行
        //这个方法几乎不用的，因为以后文件都是自动创建的！
        try {
            System.out.println(f.createNewFile());  //由于文件已经存在，所以这个创建失败
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File f1 = new File("day9-file-io-app/src/resources/data02.txt");
        try {
            System.out.println(f1.createNewFile());  //文件存在，所以创建成功
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //b.mkdir创建一级目录
        File f2 = new File("day9-file-io-app/src/resources/aaa");
        System.out.println(f2.mkdir());

        //c.mkdirs创建多级目录（重点）
        File f3 = new File("day9-file-io-app/src/resources/ccc/ddd/eee/ffff");
        System.out.println(f3.mkdir()); //如果创建多级目录，这样会报错
        System.out.println(f3.mkdirs());

        //d.删除文件或者空文件夹
        //当文件被占用时依旧能强行删除
        System.out.println(f1.delete());    //文件存在时删除返回true

        //只能删除空文件夹，不能删除非空文件夹
        System.out.println(f2.delete());    //由于是空文件夹，所以为true
        File f4 = new File("day9-file-io-app/src/resources");   //由于是非空文件夹，所以为false
        System.out.println(f4.delete());

    }
}
