package com.itheima.attention;

public class Test {
    public static void main(String[] args) {
        int[] arr = {11, 22, 33};
        //System.out.println(arr[3]); //出现异常

        arr = null;
        System.out.println(arr);
        //System.out.println(arr.length); //出现异常
        System.out.println("程序结束");

    }
}
