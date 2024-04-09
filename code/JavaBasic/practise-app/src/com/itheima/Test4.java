package com.itheima;

/**
 * 需求：把一个数组中的元素复制到另一个新数组中去
 */
public class Test4 {
    public static void main(String[] args) {
        int[] arr1 = {11, 22, 33, 44};
        int[] arr2 = new int[arr1.length];
        copyArray(arr1, arr2);
        printArray(arr1);
        printArray(arr2);
    }

    public static void copyArray(int[] arr1, int[] arr2){
        //正式完成元素的赋值
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
    }

    public static void printArray(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i == arr.length-1 ? arr[i] : arr[i] + ", ");
        }
        System.out.println("]");
    }
}
