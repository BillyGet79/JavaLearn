package com.itheima.d1_create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 目标：学会线程的创建方式三：实现Callable接口，结合FutureTask完成
 */
public class ThreadDemo33 {
    public static void main(String[] args) {
        //3、创建Callable任务对象
        Callable<String> call1 = new MyCallable(100);
        //4、把Callable任务对象   交给  FutureTask对象
        //  FutureTask对象的作用：是Runnable的对象，可以交给Thread了    可以在线程执行完毕之后通过调用其get方法得到线程执行完成的结果
        FutureTask<String> f1 = new FutureTask<>(call1);
        //5、交给线程处理
        Thread t1 = new Thread(f1);
        //6、启动线程
        t1.start();

        Callable<String> call2 = new MyCallable(200);
        FutureTask<String> f2 = new FutureTask<>(call2);
        Thread t2 = new Thread(f2);
        t2.start();

        try {
            //如果f1任务没有执行完毕，这里的代码会等待，直到线程1跑完才提取结果
            String rs1 = f1.get();
            System.out.println("第一个结果：" + rs1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            String rs2 = f2.get();
            System.out.println("第一个结果：" + rs2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * 1、定义一个任务类    实现Callable接口    应该声明线程任务执行完毕后的结果的数据类型
 */
class MyCallable implements Callable<String> {
    private int n;
    public MyCallable(int n) {
        this.n = n;
    }
    /**
     * 2、重写call方法（任务方法）
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return "子线程返回的结果是：" + sum;
    }
}
