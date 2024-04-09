package com.itheima.d5_thread_synchronized_method;


public class TestSafeDemo {
    public static void main(String[] args) {
        //测试线程安全问题
        //1、创建一个共享的账户对象
        Account acc = new Account("ICBC-111", 100000);

        //2、创建2个线程对象，代表小明和小红同时进来了
        new DrawThread(acc, "小明").start();
        new DrawThread(acc, "小红").start();
    }
}
