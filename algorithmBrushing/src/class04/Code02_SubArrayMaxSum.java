package class04;

/**
 * 题目2
 * 返回一个数组中，子数组最大累加和
 * 本题测试链接：<a href="https://leetcode.cn/problems/maximum-subarray/">...</a>
 */
public class Code02_SubArrayMaxSum {

    /**
     * 首先需要注意的是，子数组一定是连续元素的（别想你那个破背包艹）
     * 既然是子数组连续元素，我们就可以用那个思维定式，及找到任何以i位置为结尾的最大子数组和
     * 然后找最大即可
     * 对于每一个位置的元素依赖，我们可以以这样的策略去考虑：
     * 如果当前位置的元素加上上一个位置的元素比自己本身大，那么就加
     * 如果当前位置的元素加上上一个位置的元素没自己大，那就保留自己
     */
    //先写一个非空间压缩的方法
    public static int maxSubArray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];
        dp[0] = Integer.MIN_VALUE;
        int ans = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = arr[i];
            if (arr[i] < dp[i - 1] + arr[i]) {
                dp[i] += dp[i - 1];
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    //进行空间压缩
    public static int maxSubArray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int dp = arr[0];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < N; i++) {
            dp = Math.max(dp + arr[i], arr[i]);
            ans = Math.max(ans, dp);
        }
        return ans;
    }

}
