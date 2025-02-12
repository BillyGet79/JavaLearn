package com.itheima.d1_byte_buffer;

import java.io.*;

/**
 * 目标：使用字节缓冲流完成数据的读写操作
 */
public class ByteBufferDemo {
    public static void main(String[] args){
        try (

                //这里面只能放置资源对象，用完会自动关闭，自动调用资源对象的close方法关闭资源（即使出现异常也会做关闭操作）
                //创建一个字节输入流管道与原视频接通
                InputStream is = new FileInputStream("D:\\BaiduNetdiskDownload\\day08、日志框架、阶段项目\\视频\\01、今日课程安排.avi");
                //把原始的字节输入流包装成高级的缓冲字节输入流
                InputStream bis = new BufferedInputStream(is);
                OutputStream os = new FileOutputStream("D:\\JavaLearn\\code\\JavaSEProMax\\day09-file-io-app\\src\\resources\\b.avi");
                //把字节输出流管道包装成高级的缓冲字节输出流管道
                OutputStream bos = new BufferedOutputStream(os);

        ){

            //使用方法将文件中的内容提取出来
            byte[] buffer = bis.readAllBytes();
            //创建一个字节输出流管道与目标文件接通

            //将文件输出到字节流管道
            bos.write(buffer);

            System.out.println("复制完成了");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
