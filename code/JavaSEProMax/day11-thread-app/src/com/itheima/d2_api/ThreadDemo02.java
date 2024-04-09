package com.itheima.d2_api;

/**
 * 目标：线程API
 */
public class ThreadDemo02 {
    //main方法是由主线程负责调度的
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            System.out.println("输出：" + i);
            if (i == 3) {
                //让当前线程进入休眠状态
                Thread.sleep(3000);
            }
        }
    }
}
