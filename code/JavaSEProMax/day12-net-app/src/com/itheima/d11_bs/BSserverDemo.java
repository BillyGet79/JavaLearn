package com.itheima.d11_bs;


import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BSserverDemo {
    public static void main(String[] args) {
        try {
            //1、注册端口
            ServerSocket ss = new ServerSocket(8080);
            //2、创建一个循环接收多个客户端请求
            while (true) {
                Socket socket = ss.accept();
                //3、交给一个独立的线程来处理！
                new ServerReaderThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ServerReaderThread extends Thread {
    private Socket socket;
    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //浏览器 已经与本线程建立了Socket管道
            //响应消息给浏览器显示
            PrintStream ps = new PrintStream(socket.getOutputStream());
            //必须相应HTTP协议格式数据，否则浏览器不认识消息
            ps.println("HTTP/1.1 200 0K");  //协议类型和版本 响应成功的消息
            ps.println("Content-Type:text/html;charset=UTF-8"); //相应的数据类型：文本/网页
            ps.println();   //必须发送一个空行
            //才可以响应数据回去给浏览器
            ps.println("<span style='color:read;font-size:90px'>《最牛的149期》 </span>");
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

