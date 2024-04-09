package com.itheima;

/**
 * 需求：找出101-200之间的素数输出
 */
public class Test2 {
    public static void main(String[] args) {
        //1、定义一个循环，找到101-200之间的全部数据
        for (int i = 101; i <= 200; i++) {
            //3、根据判定的结果选择是否输出这个数据，是素数则输出
            if(prime(i)){
                System.out.print(i + "\t");
            }
        }
    }

    //2、判断该数是否是素数
    public static boolean prime(int n){
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
}
