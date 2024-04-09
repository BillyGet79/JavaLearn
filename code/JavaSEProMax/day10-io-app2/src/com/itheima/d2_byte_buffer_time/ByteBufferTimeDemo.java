package com.itheima.d2_byte_buffer_time;

import java.io.*;

public class ByteBufferTimeDemo {
    private static final String SRC_FILE = "D:\\BaiduNetdiskDownload\\day08、日志框架、阶段项目\\视频\\01、今日课程安排.avi";
    private static final String DEST_FILE = "D:\\JavaLearn\\code\\JavaSEProMax\\day10-io-app2\\src\\resources\\b.avi";

    public static void main(String[] args) {
        //使用低级的字节流按照一个一个字节的形式复制文件
        //copy1();  //太慢了，不测试了
        //使用低级的字节流按照一个一个字符数组的形式复制文件
        copy2();
        //缓冲流一个一个字节复制
        copy3();
        //缓冲流一个一个字节数组复制
        copy4();
    }

    //低级字节流一个一个字节的形式复制文件
    public static void copy1() {
        long startTime = System.currentTimeMillis();
        try (
                InputStream is = new FileInputStream(SRC_FILE);
                OutputStream os = new FileOutputStream(DEST_FILE);
                ){
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用低级的字节流按照一个一个字节的形式复制文件耗时：" + (endTime - startTime) / 1000.0 + "s");
    }

    //低级字节流一个一个字符数组的形式复制文件
    public static void copy2() {
        long startTime = System.currentTimeMillis();
        try(
                InputStream is = new FileInputStream(SRC_FILE);
                OutputStream os = new FileOutputStream(DEST_FILE);
                ) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用低级的字节流按照一个一个字节的形式复制文件耗时：" + (endTime - startTime) / 1000.0 + "s");
    }

    //使用缓冲流一个一个字节的形式复制文件
    public static void copy3() {
        long startTime = System.currentTimeMillis();
        try (
                InputStream is = new FileInputStream(SRC_FILE);
                InputStream bis = new BufferedInputStream(is);
                OutputStream os = new FileOutputStream(DEST_FILE);
                OutputStream bos = new BufferedOutputStream(os);
                ){
            int b;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用低级的字节流按照一个一个字节的形式复制文件耗时：" + (endTime - startTime) / 1000.0 + "s");
    }

    //使用缓冲流一个一个字节数组的形式复制文件
    public static void copy4() {
        long startTime = System.currentTimeMillis();
        try(
                InputStream is = new FileInputStream(SRC_FILE);
                InputStream bis = new BufferedInputStream(is);
                OutputStream os = new FileOutputStream(DEST_FILE);
                OutputStream bos = new BufferedOutputStream(os);
                ) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用低级的字节流按照一个一个字节的形式复制文件耗时：" + (endTime - startTime) / 1000.0 + "s");
    }
}
