package com.itheima.d2_recursion;

import java.io.File;
import java.io.IOException;

/**
 * 目标：去D盘搜索相应的文件
 */
public class RecursionDemo05 {
    public static void main(String[] args) {
        //传入目录和文件名称
        searchFile(new File("D:/"), "AxMath.exe");
    }

    /**
     * 搜索某个目录下的全部文件，找到我们想要的文件
     * @param dir
     * @param fileName
     */
    public static void searchFile(File dir, String fileName) {
        //判断dir是否是目录
        if (dir != null && dir.isDirectory()) {
            //可以找了
            //提取当前目录下的一级文件对象
            File[] files = dir.listFiles();
            //判断是否存在一级文件对象
            if (files != null && files.length > 0) {
                for (File file : files) {
                    //判断当前遍历的一级文件对象是文件还是目录
                    if (file.isFile()) {
                        //是不是咱们要找的文件，找到输出即可
                        if (file.getName().contains(fileName)) {
                            System.out.println("找到文件了：" + file.getAbsolutePath());
                            //启动它
                            try {
                                Runtime r = Runtime.getRuntime();
                                r.exec(file.getAbsolutePath());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        //是文件夹，所以要去递归寻找
                        searchFile(file, fileName);
                    }
                }
            }
        } else {
            System.out.println("对不起，当前搜索的位置不是文件夹，不支持！");
        }
    }
}
