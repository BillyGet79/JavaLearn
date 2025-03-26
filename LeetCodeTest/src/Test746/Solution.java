package Test746;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/3
 * @description 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 *
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 *
 * 请你计算并返回达到楼梯顶部的最低花费。
 */
public class Solution {
    /**
     * 先用递归的方式解决
     * @param cost
     * @return
     */
    public int minCostClimbingStairs1(int[] cost) {
        return process(cost, 0);
    }

    /**
     * 递归处理 注意i为length的时候，就为到达顶端的时候
     * @param cost  表示每个阶梯向上爬花费的费用
     * @param i     到了哪个阶梯了
     * @return      返回的最小花费是多少
     */
    public int process(int[] cost, int i) {
        //先进行条件判断，保证i不越界
        if (i > cost.length - 1) {  //此时已经到达顶端，无需再继续下去
            return 0;
        }
        //当前花费费用走一步之后的代价
        int p1 = cost[i] + process(cost, i + 1);
        //当前花费费用走两步之后的代价
        int p2 = cost[i] + process(cost, i + 2);
        return Math.min(p1, p2);
    }

    /**
     * 上述方法很明显的超时，接下来使用动态规划方法
     * @param cost
     * @return
     */
    public int minCostClimbingStairs2(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 2];
        //dp[len] = 0;
        //dp[len + 1] = 0;
        for (int i = len - 1; i >= 0; i--) {
            int p1 = cost[i] + dp[i + 1];
            int p2 = cost[i] + dp[i + 2];
            dp[i] = Math.min(p1, p2);
        }
        return dp[0];
    }


}
