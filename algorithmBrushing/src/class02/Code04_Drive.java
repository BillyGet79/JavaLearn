package class02;

import java.util.Arrays;

/**
 * 司机调度 时间限制：3000MS 内存限制：589824KB
 * 问题描述：
 * 现有司机N*2人，调度中心会叫所有司机平分给A、B两个区域
 * 第i个司机去A可得收入为income[i][0]
 * 第i个司机去B可得收入为income[i][1]
 * 返回所有调度方案中能使所有司机总收入最高的方案，是多少钱
 */
public class Code04_Drive {

    //最暴力的方法，暴力到不能再暴力了
    public static int maxMoney1(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length;
        int M = N >> 1;
        return process1(income,0, M);
    }

    public static int process1(int[][] income, int i, int left) {
        if (i == income.length) {
            return 0;
        }
        if (income.length - i == left) {
            return income[i][0] + process1(income, i + 1, left - 1);
        }
        if (left == 0) {
            return income[i][1] + process1(income, i + 1, left);
        }
        int chooseFirst = income[i][0] + process1(income, i + 1, left - 1);
        int chooseSecond = income[i][1] + process1(income, i + 1, left);
        return Math.max(chooseFirst, chooseSecond);
    }

    //严格位置依赖动态规划
    //时间复杂度为O(N^2)  （dp遍历是M * N，但是M = N >> 1）
    public static int maxMoney2(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length;
        int M = N >> 1;
        int[][] dp = new int[N + 1][M + 1];
        //base case
        //由于i = N时所有值为0，所以就不初始化了
        //动态规划迭代
        for (int i = N - 1; i >= 0 ; i--) {
            for (int left = 0; left <= M; left++) {
                if (N - i == left) {
                    dp[i][left] = income[i][0] + dp[i + 1][left - 1];
                } else if (left == 0) {
                    dp[i][left] = income[i][1] + dp[i + 1][left];
                } else {
                    int chooseFirst = income[i][0] + dp[i + 1][left - 1];
                    int chooseSecond = income[i][1] + dp[i + 1][left];
                    dp[i][left] = Math.max(chooseFirst, chooseSecond);
                }
            }
        }
        return dp[0][M];
    }

    //这道题的贪心策略
    //我们可以假设所有的司机都去A，得到一个总收益
    //然后看看其中一半的司机去B，可以获得最大的额外收益
    //这样解答的时间复杂度为O(NlogN)，时间复杂度主要是花费在排序上
    public static int maxMoney3(int[][] income) {
        int N = income.length;
        //用arr数组记录income[i]的两个地方的差值
        int[] arr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = income[i][1] - income[i][0];
            sum += income[i][0];
        }
        //然后将这个差值数组排序
        //这样后一半的差值就是我们要在sum中调整的
        Arrays.sort(arr);
        int M = N >> 1;
        //把后一半的值全部加到sum中，最后得到结果
        for (int i = N - 1; i >= M; i--) {
            sum += arr[i];
        }
        return sum;
    }


    // 找到了leetcode上的测试
    // leetcode上让求最小，课上讲的求最大
    // 其实是一个意思
    // 测试链接 : https://leetcode.cn/problems/two-city-scheduling/
    public static int twoCitySchedCost(int[][] costs) {
        int N = costs.length;
        int[] arr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = costs[i][1] - costs[i][0];
            sum += costs[i][0];
        }
        Arrays.sort(arr);
        int M = N >> 1;
        for (int i = 0; i < M; i++) {
            sum += arr[i];
        }
        return sum;
    }

    // 返回随机len*2大小的正数矩阵
    // 值在0~value-1之间
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            int ans2 = maxMoney2(matrix);
            int ans3 = maxMoney3(matrix);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
