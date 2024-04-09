package class41;

import java.io.FileReader;

public class Code03_StoneMerge {

    //求前缀和（前面留一位0）
    public static int[] sum(int[] arr) {
        int N = arr.length;
        int[] s = new int[N + 1];
        s[0] = 0;
        for (int i = 0; i < N; i++) {
            s[i + 1] = s[i] + arr[i];
        }
        return s;
    }

    //求L到R区间和
    public static int w(int[] s, int L, int R) {
        return s[R + 1] - s[L];
    }

    //暴力递归方法
    public static int min1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum(arr);
        return process1(0, N - 1, s);
    }

    public static int process1(int L, int R, int[] s) {
        if (L == R) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        //这里使用了暴力枚举的方式来解决
        for (int leftEnd = L; leftEnd < R; leftEnd++) {
            next = Math.min(next, process1(L, leftEnd, s) + process1(leftEnd + 1, R, s));
        }
        return next + w(s, L, R);
    }

    //改写动态规划方法
    public static int min2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum(arr);
        int[][] dp = new int[N][N];
        //这里做初始化，每一个dp[i][i]都为0，所以可以什么也不用做
        for (int L = N - 2; L >= 0; L--) {
            for (int R = L + 1; R < N; R++) {
                int next = Integer.MAX_VALUE;
                for (int leftEnd = L; leftEnd < R; leftEnd++) {
                    next = Math.min(next, dp[L][leftEnd] + dp[leftEnd + 1][R]);
                }
                dp[L][R] = next + w(s, L, R);
            }
        }
        return dp[0][N - 1];
    }

    public static int min3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum(arr);
        int[][] dp = new int[N][N];
        int[][] best = new int[N][N];
        //初始化数组，需要注意best数组的初始化
        for (int i = 0; i < N - 1; i++) {
            best[i][i + 1] = i;
            dp[i][i + 1] = w(s, i, i + 1);
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int next = Integer.MAX_VALUE;
                //choose记录划分的位置
                int choose = -1;
                for (int leftEnd = best[L][R - 1]; leftEnd <= best[L + 1][R]; leftEnd++) {
                    //找到当前划分的最优解
                    int cur = dp[L][leftEnd] + dp[leftEnd + 1][R];
                    //如果当前的划分比next小，就说明这个划分比之前的划分策略更好，所以记录这个划分
                    if (cur <= next) {
                        next = cur;
                        choose = leftEnd;
                    }
                }
                //最后将划分点记录到best数组当中
                best[L][R] = choose;
                dp[L][R] = next + w(s, L, R);
            }
        }
        return dp[0][N - 1];
    }

    //对数器
    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 15;
        int maxValue = 100;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, maxValue);
            int ans1 = min1(arr);
            int ans2 = min2(arr);
            int ans3 = min3(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
