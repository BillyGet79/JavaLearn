package com.itheima.d2_recursion;

public class RecursionDemo04 {
    public static void main(String[] args) {
        System.out.println(firstDayPeach(1));
    }

    /**
     * 猴子摘桃问题
     * @param n
     * @return
     */
    public static int firstDayPeach(int n) {
        if (n == 10) {
            return 1;
        } else {
            return 2 * firstDayPeach(n + 1) + 2;
        }
    }
}
