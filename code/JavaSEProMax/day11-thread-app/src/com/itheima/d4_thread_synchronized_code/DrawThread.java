package com.itheima.d4_thread_synchronized_code;

/**
 * 取钱的线程类
 */
public class DrawThread extends Thread{
    //接收处理的账户对象
    private Account acc;

    public DrawThread(Account acc, String name) {
        super(name);
        this.acc = acc;
    }

    @Override
    public void run() {
        //小明    小红  取钱的
        acc.drawMoney(100000);
    }
}
