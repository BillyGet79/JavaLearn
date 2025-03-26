package Test416;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/3
 * @description 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class Solution {
    /**
     * 先用暴力递归解决
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        return process(nums, 0, 0, 0);
    }

    public boolean process(int[] nums, int i, int sum1, int sum2) {
        //先进行判出条件
        if (i > nums.length - 1) {
            return sum1 == sum2;
        }
        return process(nums, i + 1, sum1 + nums[i], sum2) || process(nums, i + 1, sum1, sum2 + nums[i]);
    }

    /**
     * 动态规划解决
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int len = nums.length;
        //先计算nums总和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        boolean[][][] dp = new boolean[len + 1][sum + 1][sum + 1];
        //将i为len上的所有对角线元素全部置为true
        for (int i = 0; i <= sum; i++) {
            dp[len][i][i] = true;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int sum1 = sum; sum1 >= 0; sum1--) {
                for (int sum2 = sum; sum2 >= 0; sum2--) {
                    boolean p1 = false;
                    if (sum1 + nums[i] <= sum) {
                        p1 = dp[i + 1][sum1 + nums[i]][sum2];
                    }
                    boolean p2 = false;
                    if (sum2 + nums[i] <= sum) {
                        p2 = dp[i + 1][sum1][sum2 + nums[i]];
                    }
                    dp[i][sum1][sum2] = p1 || p2;
                }
            }
        }
        return dp[0][0][0];
    }

    /**
     * 上一个算法超出了内存限制，需要做空间压缩
     * 我们注意到，每一个i都仅仅依赖上一个i+1的一个由sum控制的二维数组，所以可以通过这一点将三维dp压缩为二维dp
     * @param nums
     * @return
     */
    public boolean canPartition3(int[] nums) {
        int len = nums.length;
        //先计算nums总和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        boolean[][] dp = new boolean[sum + 1][sum + 1];
        //当前视为上一个算法当中的最后一层二维数组，对其进行初始化
        for (int i = 0; i <= sum; i++) {
            dp[i][i] = true;
        }
        //定义一个辅助的dp数组，用来保存当前这一层的结果
        boolean[][] dp1 = new boolean[sum + 1][sum + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int sum1 = sum; sum1 >= 0; sum1--) {
                for (int sum2 = sum; sum2 >= 0; sum2--) {
                    boolean p1 = false;
                    if (sum1 + nums[i] <= sum) {
                        p1 = dp[sum1 + nums[i]][sum2];
                    }
                    boolean p2 = false;
                    if (sum2 + nums[i] <= sum) {
                        p2 = dp1[sum1][sum2 + nums[i]];
                    }
                    dp1[sum1][sum2] = p1 || p2;
                }
            }
            //到这里就做完这一层了，然后改变指针，让dp指向dp1，dp1再指向原先的dp，然后刷新二维数组
            boolean[][] temp = dp;
            dp = dp1;
            dp1 = temp;
            Arrays.fill(dp1, false);
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canPartition1(new int[]{1, 5, 11, 5}));
    }

}
