package class39;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class Code01_SubsquenceMaxModM {

    //暴力递归方法
    //其实就是得到所有可能出现的总和，将其记录在set当中，然后一个一个拿出来计算取最大即可
    public static int max1(int[] arr, int m) {
        HashSet<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);
        int max = 0;
        for (Integer sum : set) {
            max = Math.max(max, sum % m);
        }
        return max;
    }

    public static void process(int[] arr, int index, int sum, HashSet<Integer> set) {
        if (index == arr.length) {
            set.add(sum);
        } else {
            process(arr, index + 1, sum, set);
            process(arr, index + 1, sum + arr[index], set);
        }
    }

    public static int max2(int[] arr, int m) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int N = arr.length;
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] |= dp[i - 1][j - arr[i]];
                }
            }
        }
        //最后一行进行遍历，值为true的时候，其纵坐标就是能得到的总和，对m取余即可
        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            if (dp[N - 1][i]) {
                ans = Math.max(ans, i % m);
            }
        }
        return ans;
    }

    public static int max3(int[] arr, int m) {
        int N = arr.length;
        boolean[][] dp = new boolean[N][m];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j];
                //这里注意模m剩余类加群的实现
                int cur = arr[i] % m;
                if (j - cur >= 0) {
                    dp[i][j] |= dp[i - 1][j - cur];
                } else {
                    dp[i][j] |= dp[i - 1][m + j - cur];
                }
            }
        }
        int ans = 0;
        for (int i = m - 1; i >= 0; i--) {
            if (dp[N - 1][i]) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static int max4(int[] arr, int m) {
        if (arr.length == 1) {
            return arr[0] % m;
        }
        //分治，找中位数
        int mid = (arr.length - 1) / 2;
        //前半个数组处理
        TreeSet<Integer> sortSet1 = new TreeSet<>();
        process4(arr, 0, 0, mid, m, sortSet1);
        //后半个数组处理
        TreeSet<Integer> sortSet2 = new TreeSet<>();
        process4(arr, mid + 1, 0, arr.length - 1, m, sortSet2);
        //遍历寻找答案
        int ans = 0;
        for (Integer leftMod : sortSet1) {
            ans = Math.max(ans, leftMod + sortSet2.floor(m - 1 - leftMod));
        }
        return ans;
    }

    public static void process4(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
        if (index == end + 1) {
            sortSet.add(sum % m);
        } else {
            process4(arr, index + 1, sum, end, m, sortSet);
            process4(arr, index + 1, sum + arr[index], end, m, sortSet);
        }
    }

    //对数器
    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 100;
        int m = 76;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int ans1 = max1(arr, m);
            int ans2 = max2(arr, m);
            int ans3 = max3(arr, m);
            int ans4 = max4(arr, m);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("Oops!");
                System.out.println(ans1 + " " + ans2 + " " + ans3 + " " + ans4);
            }
        }
        System.out.println("test finish!");
    }
}