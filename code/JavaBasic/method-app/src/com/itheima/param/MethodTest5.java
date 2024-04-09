package com.itheima.param;

public class MethodTest5 {
    public static void main(String[] args) {
        //需求：比较任意2个任意整型数组的内容是否一样
        int[] arr1 = {10, 120, 30};
        int[] arr2 = {10, 20, 30};
        System.out.println(compare(arr1, arr2));
    }

    /**
     * 1、定义一个方法：参数：接收两个整型数组 返回值类型：布尔类型
     */
    public static boolean compare(int[] arr1, int[] arr2){
        if (arr1.length == arr2.length){
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] != arr2[i]){
                    return false;
                }
            }
            return true;
        }else {
            return false;
        }
    }
}
