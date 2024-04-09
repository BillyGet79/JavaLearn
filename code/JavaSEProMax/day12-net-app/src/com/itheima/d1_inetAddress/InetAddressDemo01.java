package com.itheima.d1_inetAddress;

import java.net.InetAddress;

public class InetAddressDemo01 {
    public static void main(String[] args) throws Exception {
        //1、获取本机地址对象
        InetAddress ip1 = InetAddress.getLocalHost();
        System.out.println(ip1.getHostName());
        System.out.println(ip1.getHostAddress());

        //2、获取域名ip对象
        InetAddress ip2 = InetAddress.getByName("www.baidu.com");
        System.out.println(ip2.getHostName());
        System.out.println(ip2.getHostAddress());

        //3、获取公网ip对象
        InetAddress ip3 = InetAddress.getByName("183.2.172.185");
        System.out.println(ip3.getHostName());
        System.out.println(ip3.getHostAddress());

        //4、判断是否能通： ping    5s之前测试是否可通
        System.out.println(ip3.isReachable(5000));


    }
}
