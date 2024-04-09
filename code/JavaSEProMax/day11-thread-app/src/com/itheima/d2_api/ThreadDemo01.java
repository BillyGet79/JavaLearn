package com.itheima.d2_api;

/**
 * 目标：线程API
 */
public class ThreadDemo01 {
    //main方法是由主线程负责调度的
    public static void main(String[] args) {
        Thread t1 = new MyThread("1号");
        //t1.setName("1号");
        t1.start();
        System.out.println(t1.getName());

        Thread t2 = new MyThread("2号");
        //t2.setName("2号");
        t2.start();
        System.out.println(t2.getName());

        //哪个线程执行它，它就得到哪个线程对象（当前线程对象）
        //主线程的名称就叫main
        Thread m = Thread.currentThread();
        m.setName("最牛的线程");
        System.out.println(m.getName());

        for (int i = 0; i < 5; i++) {
            System.out.println(m.getName() + "输出：" + i);
        }
    }
}
