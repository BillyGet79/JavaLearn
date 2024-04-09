package com.itheima.create;

public class MethodDemo2 {
    public static void main(String[] args) {
        //目标：学习方法的完整定义格式，并理解其调用和执行流程
        System.out.println("和是：" + add(100, 200));

        System.out.println("----------------");
        System.out.println("和是：" + add(200, 300));
    }

    public static int add(int a, int b){
        return a + b;
    }
}
