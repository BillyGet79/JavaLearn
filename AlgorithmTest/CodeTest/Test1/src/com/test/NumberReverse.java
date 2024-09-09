package com.test;

import java.util.Arrays;

/**
 *
 */

public class NumberReverse {
    public static void main(String[] args) {

    }

    public static int getMaxSumAfterKFlips(int[] a, int K) {
        // 对数组进行排序，以找到最小的 K 个元素
        Arrays.sort(a);

        // 翻转最小的 K 个元素的符号
        for (int i = 0; i < K; i++) {
            a[i] = -a[i];
        }

        // 计算翻转后的数组的总和
        int totalSum = 0;
        for (int num : a) {
            totalSum += num;
        }

        // 如果 K 是偶数，则翻转操作已经完成；如果 K 是奇数，则可能需要调整最小值来获得最大和
        if (K % 2 != 0) {
            // 找出数组中的最小绝对值元素并反转其符号以获得最大总和
            int minAbs = Integer.MAX_VALUE;
            for (int num : a) {
                minAbs = Math.min(minAbs, Math.abs(num));
            }
            totalSum -= 2 * minAbs;
        }

        return totalSum;
    }
}
