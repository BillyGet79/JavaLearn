package com.itheima.d4_byte_stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamDemo04 {
    public static void main(String[] args) throws IOException {
        //1、创建一个文件字节输出流管道与目标文件接通
        //如果文件不存在会自动创建
        //OutputStream os = new FileOutputStream("day9-file-io-app\\src\\resources\\out04.txt");  //先清空之前的数据，写新数据进入
        OutputStream os = new FileOutputStream("day9-file-io-app\\src\\resources\\out04.txt", true);    //这样可以在文件后面追加内容
        //2、写数据出去
        //写一个字节出去
        os.write('a');
        os.write(98);
        os.write("\r\n".getBytes());
        //os.write('徐');

        //写一个字节数组出去
        byte[] buffer = {'a', 97, 98, 99};
        os.write(buffer);
        os.write("\r\n".getBytes());

        byte[] buffer2 = "我是中国人".getBytes();
        os.write(buffer2);
        os.write("\r\n".getBytes());

        //写一个字节数组的一部分出去
        byte[] buffer3 = {'a', 97, 98, 99};
        os.write(buffer3, 0, 3);
        os.write("\r\n".getBytes());


        os.flush(); //写数据必须刷新数据 可以继续使用流
        os.close(); //释放资源
    }
}
