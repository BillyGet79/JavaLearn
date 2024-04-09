package com.itheima.d4_byte_stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 目标；学会使用字节流完成文件的复制（支持一切文件类型的复制）
 */
public class CopyDemo05 {
    public static void main(String[] args) throws IOException {
        //1、创建一个字节输入流管道与原视频接通
        FileInputStream is = new FileInputStream("D:\\BaiduNetdiskDownload\\day08、日志框架、阶段项目\\视频\\01、今日课程安排.avi");
        byte[] buffer = is.readAllBytes();
        FileOutputStream os = new FileOutputStream("day9-file-io-app\\src\\resources\\b.avi");
        os.write(buffer);
        os.flush();
        os.close();
        is.close();
    }
}
