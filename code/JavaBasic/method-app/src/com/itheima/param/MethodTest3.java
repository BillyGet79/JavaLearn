package com.itheima.param;

public class MethodTest3 {
    public static void main(String[] args) {
        //需求：打印任意整型数组的内容
        int[] ages = {10, 20, 30, 40};
        printArray(ages);

        System.out.println("------------------");
        int[] numbers = {1, 2, 3, 9, 10};
        printArray(numbers);

        System.out.println("------------------");
        int[] numbers1 = null;
        printArray(numbers1);
    }

    public static void printArray(int[] arr){
        System.out.print("该数组的内容为：");
        System.out.print("[");
        if (arr != null && arr.length > 0){
            for (int i = 0; i < arr.length; i++) {
                System.out.print(i == arr.length - 1 ? arr[i] : arr[i] + ", ");
            }
        }
        System.out.println("]");
    }
}
