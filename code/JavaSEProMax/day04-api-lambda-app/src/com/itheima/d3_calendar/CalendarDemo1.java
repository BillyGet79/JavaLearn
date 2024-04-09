package com.itheima.d3_calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo1 {
    public static void main(String[] args) {
        //1、拿到系统此刻日历对象
        Calendar cal = Calendar.getInstance();
        System.out.println(cal);

        //2、获取日历的信息
        int year = cal.get(Calendar.YEAR);
        System.out.println(year);
        //月份是从0开始记月
        int month = cal.get(Calendar.MONTH) + 1;
        System.out.println(month);
        int days = cal.get(Calendar.DAY_OF_YEAR);
        System.out.println(days);

        //3、修改日历的某个字段信息
        //cal.set(Calendar.HOUR, 12);
        //System.out.println(cal);

        //4、为某个字段增加/减少指定的值
        //请问64天后是什么时间
        cal.add(Calendar.DAY_OF_YEAR, 64);

        //5、拿到此刻日期对象
        Date d = cal.getTime();
        System.out.println(d);

        //6、拿到此刻时间毫秒值
        long time = cal.getTimeInMillis();
        System.out.println(time);
    }
}
