package com.itheima.demo;

public class Test5 {
    public static void main(String[] args) {
        //1、定义一个数组，存储一些数据
        int[] arr = {5, 2, 3, 1};

        //2、冒泡排序的实现
        boolean finish = false;
        while (!finish){
            finish = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if(arr[i] > arr[i + 1]){
                    finish = false;
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }

        //3、输出排序结果
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
