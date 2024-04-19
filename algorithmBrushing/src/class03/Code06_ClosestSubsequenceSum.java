package class03;

import java.util.Arrays;
import java.util.Map;

/**
 * 题目6
 * 给你一个整数数组 nums 和一个目标值 goal 。
 * 你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal 。也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum - goal) 。
 * 返回 abs(sum - goal) 可能的 最小值 。
 * 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
 * 本题测试链接 : <a href="https://leetcode.cn/problems/closest-subsequence-sum/">...</a>
 */
public class Code06_ClosestSubsequenceSum {

    //这道题乍一眼看是背包问题，但是我们可以观察LeetCode中给出的数据量：
    //1 <= nums.length <= 40
    //-10^7 <= nums[i] <= 10^7
    //-10^9 <= goal <= 10^9
    //所以动态规划方法直接被PASS，因为表会爆
    //数组的长度比较短，我们可以用分治的方式进行解决

    //我们将数组分成前后两个部分，然后分别找前后两个部分的所有情况的累加和
    //最终的结果会出现三种情况，即前半部分的累加和，后半部分的累加和以及前后两个部分的累加和的累加和
    //这三种情况找最接近即可

    //用两个数组分别保存所有累加和的结果
    public static int[] l = new int[1 << 20];
    public static int[] r = new int[1 << 20];

    public static int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        //用递归填充l和r数组，并且返回其填充的数量
        int le = process(nums, 0, nums.length >> 1, 0, 0, l);
        int re = process(nums, nums.length >> 1, nums.length, 0, 0, r);
        //排序，排序后，l我们可以从左侧遍历，r我们可以从右侧遍历
        Arrays.sort(l, 0, le);
        Arrays.sort(r, 0, re--);
        int ans = Math.abs(goal);
        for (int i = 0; i < le; i++) {
            int rest = goal - l[i];
            //r数组从右往左找，直到找到在当前l[i]的情况下的最优解
            while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
                re--;
            }
            ans = Math.min(ans, Math.abs(rest - r[re]));
        }
        return ans;
    }

    public static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
        if (index == end) {
            arr[fill++] = sum;
        } else {
            fill = process(nums, index + 1, end, sum, fill, arr);
            fill = process(nums,  index + 1, end, sum + arr[index], fill, arr);
        }
        return fill;
    }


}
