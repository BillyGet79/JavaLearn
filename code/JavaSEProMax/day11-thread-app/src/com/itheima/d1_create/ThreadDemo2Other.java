package com.itheima.d1_create;

/**
 * 目标：实现线程的创建方式二（匿名内部类方式实现，语法形式）
 */
public class ThreadDemo2Other {
    public static void main(String[] args) {
        //3、创建一个任务对象
        Runnable target = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("子线程1执行输出：" + i);
                }
            }
        };
        Thread t = new Thread(target);
        t.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("子线程2执行输出：" + i);
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("子线程3执行输出：" + i);
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            System.out.println("主线程执行输出：" + i);
        }

    }
}

