package class04;

/**
 * 题目4
 * 打家劫舍问题
 * 返回一个数组中，选择的数字不能相邻的情况下
 * 最大子序列累加和
 * 在线测试链接 : <a href="https://leetcode.cn/problems/house-robber/">...</a>
 */
public class Code04_SubArrayMaxSumFollowUp {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = Math.max(0, nums[0]);
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(Math.max(dp[i - 1], nums[i]), nums[i] + dp[i - 2]);
        }
        return dp[N - 1];
    }

}
