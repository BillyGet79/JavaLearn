package com.itheima.d5_resource;

import java.io.*;

/**
 * 目标；学会使用finally释放资源
 */
public class TryCatchResourceDemo2 {
    public static void main(String[] args){
        try (
                //这里面只能放置资源对象，用完会自动关闭，自动调用资源对象的close方法关闭资源（即使出现异常也会做关闭操作）
                //创建一个字节输入流管道与原视频接通
                InputStream is = new FileInputStream("D:\\BaiduNetdiskDownload\\day08、日志框架、阶段项目\\视频\\01、今日课程安排.avi");
                OutputStream os = new FileOutputStream("day9-file-io-app\\src\\resources\\b.avi");

                MyConnection connection = new MyConnection();   //最终会自动调用资源的close方法
                ){

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

    public static int test(int a, int b) {
        try {
            int c = a / b;
            return c;
        }catch (Exception e) {
            e.printStackTrace();
            return -1111111;    //计算出现bug
        }finally {
            //如果在finally中写return，会抢上面的return的结果
            return 100;
        }
    }
}

class MyConnection implements AutoCloseable{

    @Override
    public void close() throws IOException {
        System.out.println("连接资源被成功释放了！");
    }
}
