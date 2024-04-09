package class42;

import java.util.Arrays;

public class Code01_PostOfficeProblem {

    public static int min1(int[] arr, int num) {
        //考虑现实情况，如果我们的居民点数量比邮局小，那么每个居民点都装一个邮局，代价一定为0
        if (arr == null || arr.length < num || num < 1) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        //计算一个邮局的情况下的所有范围的最小代价，没计算到的代价都为0
        //因为L一定小于等于R，但是当L==R的时候，代价一定为0，所以不用计算
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                //除2之后得到的值就是邮局的位置，而且不需要额外的变量来记录邮局位置
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        int[][] dp = new int[N][num + 1];
        //初始化
        //对于i=0这一行，除了j=0不用考虑以外，其他所有情况代价都为0
        //对于j=0，这一列不用考虑
        //对于j=1，我们需要将其值用上面的w数组进行赋值
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
        }
        //进行元素依赖计算
        for (int i = 1; i < N; i++) {
            //如果j超过了i，那么就会无意义，如果j的最大值一定是num
            for (int j = 2; j <= Math.min(i, num); j++) {
                int ans = Integer.MAX_VALUE;
                //L<R为无效范围，但是在w当中的值全部为0，所以我们这里就用不着边界判断了
                for (int leftEnd = 0; leftEnd <= i; leftEnd++) {
                    ans = Math.min(ans, dp[leftEnd][j - 1] + w[leftEnd + 1][i]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][num];
    }

    //利用四边形不等式优化
    public static int min2(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        int[][] dp = new int[N][num + 1];
        int[][] best = new int[N][num + 1];
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
            best[i][1] = -1;
        }
        for (int j = 2; j <= num; j++) {
            for (int i = N - 1; i >= j; i--) {
                int down = best[i][j - 1];
                int up = i == N - 1 ? N - 1 : best[i + 1][j];
                int ans= Integer.MAX_VALUE;
                int bestChoose = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : w[leftEnd + 1][i];
                    int cur = leftCost + rightCost;
                    if (cur <= ans) {
                        ans = cur;
                        bestChoose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestChoose;
            }
        }
        return dp[N - 1][num];
    }

    //对数器
    // for test
    public static int[] randomSortedArray(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i != len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        Arrays.sort(arr);
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int N = 30;
        int maxValue = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] arr = randomSortedArray(len, maxValue);
            int num = (int) (Math.random() * N) + 1;
            int ans1 = min1(arr, num);
            int ans2 = min2(arr, num);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(num);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }
}
