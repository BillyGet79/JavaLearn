package com.itheima.d2_recursion;

/**
 * 递归的形式
 */
public class RecursionDemo01 {
    public static void main(String[] args) {
        test2();
    }

    public static void test() {
        System.out.println("=======test被执行=======");
        test();
    }

    public static void test2() {
        System.out.println("=======test2被执行=======");
        test3();
    }

    public static void test3() {
        System.out.println("=======test3被执行=======");
        test2();
    }


}
