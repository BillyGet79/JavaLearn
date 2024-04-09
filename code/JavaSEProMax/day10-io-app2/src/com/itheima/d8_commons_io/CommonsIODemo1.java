package com.itheima.d8_commons_io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CommonsIODemo1 {
    public static void main(String[] args) throws Exception {
        //完成文件复制
        //IOUtils.copy(new FileInputStream("day10-io-app2\\src\\resources\\out01.txt"), new FileOutputStream("day10-io-app2\\src\\resources\\out04.txt"));

        //完成文件复制到某个文件夹下
        FileUtils.copyFileToDirectory(new File("D:\\JavaLearn\\code\\JavaSEProMax\\day09-file-io-app\\src\\resources\\out08.txt"), new File("day10-io-app2\\src\\resources"));

        //完成文件夹复制到某个文件夹下
        //FileUtils.copyDirectoryToDirectory(new File(""), new File(""));

        //删除文件夹，将文件夹以及文件夹中的所有文件全部删除
        //FileUtils.deleteDirectory(new File(""));

        //JDK1.7    自己也做了一些一行代码完成复制的操作：New IO的技术
        Files.copy(Path.of(""), Path.of(""));

        //只能删除空文件夹
        Files.deleteIfExists(Path.of(""));
    }
}
