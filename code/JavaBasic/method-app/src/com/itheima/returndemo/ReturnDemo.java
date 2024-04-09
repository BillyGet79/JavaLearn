package com.itheima.returndemo;

public class ReturnDemo {
    public static void main(String[] args) {
        //目标：明确return关键字的作用
        chu(10, 0);
    }

    public static void chu(int a, int b){
        if (b == 0){
            System.out.println("您输入的数据有问题！");
            return; //立即跳出当前方法，并结束当前方法的执行
        }
        int c = a / b;
        System.out.println("结果是：" + c);
    }
}
