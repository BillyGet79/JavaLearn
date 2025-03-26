package Test518;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/3
 * @description 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 *
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 *
 * 假设每一种面额的硬币有无限个。
 *
 * 题目数据保证结果符合 32 位带符号整数。
 */
public class Solution {
    /**
     * 动态规划方法
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        //dp[i]定义为，金额值为i的情况下有多少组合数
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= amount; i++) {

        }
        return dp[amount] = 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.change(5, new int[]{1, 2, 5}));
    }
}
