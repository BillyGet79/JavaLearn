package com.itheima;

import java.util.Scanner;

/**
 * 需求：
 *      某系统的数字密码：比如1983，采用加密方式进行传输，规则如下：
 *      先得到每位数，然后每位数都加上5，再对10求余，最后将所有数字反转，得到一串新数
 */
public class Test6 {
    public static void main(String[] args) {
        //1、定义一个数组存入需要加密的数据
        System.out.println("请您输入需要加密的数字个数");
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] arr = new int[length];

        //2、录入需要加密的数字
        for (int i = 0; i < arr.length; i++) {
            System.out.println("请您输入加密的第" + (i + 1) + "个数字：");
            arr[i] = sc.nextInt();
        }
        //3、打印加密前的内容
        System.out.println("加密前的内容为：");
        printArray(arr);
        //4、加密操作
        encrypt(arr);
        //5、打印加密后的内容
        System.out.println("加密后的内容为：");
        printArray(arr);
    }

    //打印数组
    public static void printArray(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i == arr.length - 1 ? arr[i] : arr[i] + ", ");
        }
        System.out.println("]");
    }

    //数字加密
    public static void encrypt(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] + 5) % 10;
        }
        reverse(arr);
    }

    public static void reverse(int[] arr){
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
