package com.itheima.d5_resource;

import java.io.*;

/**
 * 目标；JDK9释放资源的方式，可以了解下
 */
public class TryCatchResourceDemo3 {
    public static void main(String[] args) throws Exception {
        //创建一个字节输入流管道与原视频接通
        InputStream is = new FileInputStream("D:\\BaiduNetdiskDownload\\day08、日志框架、阶段项目\\视频\\01、今日课程安排.avi");
        OutputStream os = new FileOutputStream("day9-file-io-app\\src\\resources\\b.avi");
        try (is; os){

            //使用方法将文件中的内容提取出来
            byte[] buffer = is.readAllBytes();
            //创建一个字节输出流管道与目标文件接通

            //将文件输出到字节流管道
            os.write(buffer);

            System.out.println("复制完成了");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

