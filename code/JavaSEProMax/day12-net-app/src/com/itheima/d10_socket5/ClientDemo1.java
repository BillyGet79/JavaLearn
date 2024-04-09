package com.itheima.d10_socket5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 1.客户端发送消息
 * 2.客户端随时可能需要收到消息的
 */

public class ClientDemo1 {
    public static void main(String[] args) {
        try {
            System.out.println("===客户端启动成功===");
            //1、创建Socket通信管道请求有服务端的连接
            //参数一：服务端IP地址
            //参数二：服务端的端口
            Socket socket = new Socket("127.0.0.1", 7777);

            //创建一个独立的线程专门负责这个客户端的读消息（服务端随时可能转发消息过来）
            new ClientReaderThread(socket).start();

            //2、从socket通信管道中得到一个字节输出流，负责发送数据
            OutputStream os = socket.getOutputStream();

            //3、把低级的字节流包装成打印流
            PrintStream ps = new PrintStream(os);

            Scanner sc = new Scanner(System.in);

            while (true) {
                //4、发送消息
                System.out.println("请说：");
                String msg = sc.nextLine();
                //服务端收一行消息才能结束接收，所以这里一定得换行
                ps.println(msg);
                ps.flush();
            }

            //关闭资源（不建议去关）
            //socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientReaderThread extends Thread {
    private Socket socket;
    public ClientReaderThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println("收到消息：" + msg);
            }
        } catch (Exception e) {
            System.out.println("服务端把你踢出去了~~~~");
        }
    }
}
