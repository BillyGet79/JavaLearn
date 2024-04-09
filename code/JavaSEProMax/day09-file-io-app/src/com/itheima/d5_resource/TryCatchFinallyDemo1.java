package com.itheima.d5_resource;

import java.io.*;

/**
 * 目标；学会使用finally释放资源
 */
public class TryCatchFinallyDemo1 {
    public static void main(String[] args){
        InputStream is = null;
        OutputStream os = null;
        try {
            //创建一个字节输入流管道与原视频接通
            is = new FileInputStream("D:\\BaiduNetdiskDownload\\day08、日志框架、阶段项目\\视频\\01、今日课程安排.avi");
            //使用方法将文件中的内容提取出来
            byte[] buffer = is.readAllBytes();
            //创建一个字节输出流管道与目标文件接通
            os = new FileOutputStream("day9-file-io-app\\src\\resources\\b.avi");
            //将文件输出到字节流管道
            os.write(buffer);

            System.out.println("复制完成了");
            //即使写了return，finally块也会执行
            return;

            //System.out.println(10 / 0);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //无论代码是正常结束，还是出现异常都要最后执行这里
            System.out.println("============finally==========");
            //关闭流
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
