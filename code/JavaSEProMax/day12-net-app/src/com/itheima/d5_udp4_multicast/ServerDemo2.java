package com.itheima.d5_udp4_multicast;

import java.net.*;

/**
 * 接收端
 */
public class ServerDemo2 {
    public static void main(String[] args) throws Exception{
        System.out.println("======服务端启动======");
        //1、创建接收端对象：注册端口
        MulticastSocket socket = new MulticastSocket(9999);

        //把当前接收端加入到一个组播组中去
        //socket.joinGroup(InetAddress.getByName("224.0.1.1"));
        socket.joinGroup(new InetSocketAddress(InetAddress.getByName("224.0.1.1"), 9999),
                NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));

        //2、创建一个数据包对象接收数据
        byte[] buffer = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (true) {
            //3、等待接收数据即可
            socket.receive(packet);

            String ip = packet.getAddress().getHostAddress();
            int port = packet.getPort();

            //4、取出数据即可
            //读取多少倒出多少
            int len = packet.getLength();
            String rs = new String(buffer, 0, len);
            System.out.println("收到了来自：" + ip + "," +
                    "对方端口是" + port + "的消息：" + rs);

        }
    }
}
