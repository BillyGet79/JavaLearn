package com.itheima.d1_file;

import java.io.File;
import java.util.Arrays;

/**
 * 目标：File针对目录的遍历
 */
public class FileDemo04 {
    public static void main(String[] args) {
        //1、定位一个目录
        File f1 = new File("day9-file-io-app\\src\\resources");
        String[] names = f1.list();
        for (String name : names) {
            System.out.println(name);
        }

        //2、一级文件对象
        //获取当前目录下所有的"一级文件对象"到一个文件对象数组中去返回（重点）
        File[] files = f1.listFiles();
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }

        //注意事项
        File dir = new File("day9-file-io-app\\src\\resources\\aaa");
        File[] files1 = dir.listFiles();
        System.out.println(files1); //由于文件夹不存在，所以为null

        File file = new File("day9-file-io-app\\src\\resources\\data.txt");
        File[] files2 = dir.listFiles();
        System.out.println(files2); //由于定位的为一个文件，所以为null

        File dir1 = new File("day9-file-io-app\\src\\resources\\ddd");
        File[] files3 = dir.listFiles();
        System.out.println(Arrays.toString(files3)); //由于文件夹内容为空，所以数组内容为空
    }
}
