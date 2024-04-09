package com.itheima.d5_udp4_multicast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * 发送端  多发 多收
 */
public class ClientDemo1 {
    public static void main(String[] args) throws Exception {
        System.out.println("======客户端启动======");
        //1、创建发送端对象：发送端自带默认端口号，也可以自己指定
        DatagramSocket socket = new DatagramSocket();

        //2、创建一个数据包对象封装数据
        /**
         * public DatagramPacket(byte[] buf, int offset, int length,
         *                           InetAddress address, int port)
         * 参数一：封装要发送的数据
         * 参数二：发送数据的大小
         * 参数三：服务端的主机IP地址
         * 参数四：服务端的端口
         */

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请说：");
            String msg = sc.nextLine();
            if ("exit".equals(msg)) {
                System.out.println("离线成功！");
                socket.close();
                break;
            }
            byte[] buffer = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
                    InetAddress.getByName("224.0.1.1"), 9999);

            //3、发送数据出去
            socket.send(packet);
        }

    }
}
