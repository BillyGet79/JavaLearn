package class24;


import java.util.LinkedList;

//测试链接：https://leetcode.cn/problems/gas-station
public class Code03_GasStation {

    //LeetCode上是一个阉割版的题目，只让返回一个可以完成的点，我们要实现的是一个布尔数组，表示每一个点是否能完成
    public static boolean[] goodArray(int[] gas, int[] cost) {
        int N = gas.length;
        boolean[] ans = new boolean[N];
        int[] arr = new int[N * 2];
        for (int i = 0; i < N; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + N] = arr[i];
        }
        for (int i = 1; i < N * 2; i++) {
            arr[i] += arr[i - 1];
        }
        int R = N - 1;
        LinkedList<Integer> qmin = new LinkedList<>();
        //先把前N个元素放到qmin中
        for (int i = 0; i <= R; i++) {
            while (!qmin.isEmpty() && arr[qmin.getLast()] >= arr[i]){
                qmin.removeLast();
            }
            qmin.addLast(i);
        }
        int beforewindow = 0;
        for (int i = 0, j = N; i < N; i++, j++) {
            if (arr[qmin.getFirst()] - beforewindow >= 0){
                ans[i] = true;
            }
            if (qmin.getFirst() == i){
                qmin.removeFirst();
            }
            while (!qmin.isEmpty() && arr[qmin.getLast()] >= arr[j]){
                qmin.removeLast();
            }
            qmin.addLast(j);
            beforewindow = arr[i];
        }
        return ans;
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] good = goodArray(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }
}
