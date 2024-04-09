package com.itheima.d1_date;

import java.util.Date;

/**
 * 目标：学会使用Date类处理时间，获取时间的信息
 */
public class DateDemo1 {
    public static void main(String[] args) {
        //1、创建一个Date类的对象，代表系统此刻日期时间对象
        Date d = new Date();
        System.out.println(d);

        //2、获取时间毫秒值
        System.out.println(d.getTime());

        System.out.println("-----------------------");
        //1、得到当前时间毫秒值
        Date d1 = new Date();
        System.out.println(d1);

        //2、当前时间往后走1小时121秒
        long time = System.currentTimeMillis();
        time += (60 * 60 + 121) * 1000;

        //3、把时间毫秒值转换成对应的日期对象
        Date d2 = new Date(time);
        System.out.println(d2);
    }
}
