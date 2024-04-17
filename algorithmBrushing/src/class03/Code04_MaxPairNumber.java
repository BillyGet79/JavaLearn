package class03;

import java.util.Arrays;

/**
 * 题目4
 * 给定一个数组arr，代表每个人的能力值。再给定一个非负数k。
 * 如果两个人能力差值正好为k，那么可以凑在一起比赛，一局比赛只有两个人
 * 返回最多可以同时有多少场比赛
 */
public class Code04_MaxPairNumber {

    //暴力方法，用作测试
    public static int maxPairNum1(int[] arr, int k) {
        if (k < 0) {
            return -1;
        }
        return process1(arr, 0, k);
    }

    public static int process1(int[] arr, int index, int k) {
        int ans = 0;
        if (index == arr.length) {
            for (int i = 1; i < arr.length; i += 2) {
                if (arr[i] - arr[i - 1] == k) {
                    ans++;
                }
            }
        } else {
            for (int r = index; r < arr.length; r++) {
                swap(arr, index, r);
                ans = Math.max(ans, process1(arr, index + 1, k));
                swap(arr, index, r);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //时间复杂度O(nlogn)
    public static int maxPairNum2(int[] arr, int k) {
        if (k < 0 || arr == null || arr.length < 2) {
            return 0;
        }
        //先排个序
        Arrays.sort(arr);
        //设定两个指针
        int left = 0;
        int right = 1;
        int ans = 0;
        //设定一个布尔数组保存已经匹配的成员
        boolean[] user = new boolean[arr.length];
        //然后遍历数组
        while (right < arr.length) {
            if (arr[right] - arr[left] == k) {
                if (right == left) {
                    right++;
                } else if (!user[left]) {
                    ans++;
                    user[left] = true;
                    user[right] = true;
                    left++;
                    right++;
                } else {
                    left++;
                }
            } else if (arr[right] - arr[left] < k) {
                right++;
            } else{
                //如果到了这一步，那么该成员就没有可以匹配的了
                //直接跳过这个成员即可
                left++;
            }
        }
        return ans;
    }

    // 为了测试
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int maxK = 5;
        int testTime = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
            int[] arr = randomArray(N, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int k = (int) (Math.random() * (maxK + 1));
            int ans1 = maxPairNum1(arr1, k);
            int ans2 = maxPairNum2(arr2, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }
}
