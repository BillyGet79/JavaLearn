package com.itheima.d13_system;

import java.util.Arrays;

public class SystemDemo {
    public static void main(String[] args) {
        System.out.println("程序开始。。。");

        //System.exit(0); //JVM终止！

        long time = System.currentTimeMillis();
        System.out.println(time);

        long startTime = System.currentTimeMillis();
        //进行时间的计算，性能分析
        for (int i = 0; i < 10000; i++) {
            System.out.println("输入：" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime)/1000.0 + "s");

        //3、做数组拷贝（了解）
        int[] arr1 = {10, 20, 30, 40, 50, 60, 70};
        int[] arr2 = new int[6];
        System.arraycopy(arr1, 3, arr2, 2, 3);
        System.out.println(Arrays.toString(arr2));

        System.out.println("程序结束。。。");
    }
}
