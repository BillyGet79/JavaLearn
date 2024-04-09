package com.itheima.d10_socket5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 拓展：使用线程池优化，实现通信
 */
public class ServerDemo2 {
    //定义一个静态的List集合存储当前全部在线的Socket管道
    public static List<Socket> allOnlineSockets = new ArrayList<>();
    public static void main(String[] args) {
        try {
            System.out.println("===服务端启动成功===");
            //1、注册端口
            ServerSocket serverSocket = new ServerSocket(7777);
            //定义一个死循环由主线程负责不断的接收客户端的Socket管道连接
            while (true) {
                //2、每接收到一个客户端的Socket管道，交给一个独立的子线程负责读取消息
                Socket socket = serverSocket.accept();
                System.out.println(socket.getRemoteSocketAddress() + "它来了，上线了！");
                allOnlineSockets.add(socket);   //上线完成
                //3、开始创建独立线程处理socket
                new ServerReaderThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class ServerReaderThread extends Thread{
    private Socket socket;
    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println(socket.getRemoteSocketAddress() + "说了：" + msg);
                //把这个消息进行端口转发给全部客户端socket管道
                sendMsgToAll(msg);
            }
        } catch (Exception e) {
            System.out.println(socket.getRemoteSocketAddress() + "下线了！！！");
            ServerDemo2.allOnlineSockets.remove(socket);
        }
    }

    private void sendMsgToAll(String msg) throws Exception {
        for (Socket socket : ServerDemo2.allOnlineSockets) {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println(msg);
            ps.flush();
        }
    }
}