package Test100106;

import java.util.Stack;

public class Solution {

    //这道题的求解方法：定义一个当前值的前缀最小值数组和后缀最小值数组，遍历得到所有数的前缀最小值和后缀最小值
    public int minimumSum(int[] nums) {
        if (nums == null || nums.length < 3){
            return -1;
        }
        int N = nums.length;
        int[] pre = new int[N];
        int[] next = new int[N];
        pre[0] = nums[0];
        //求每个数的前缀最小值
        for (int i = 1; i < N - 1; i++){
            pre[i] = Math.min(pre[i - 1], nums[i - 1]);
        }
        //求每个数的后缀最小值
        next[N - 1] = nums[N - 1];
        for (int i = N - 2; i > 0; i--){
            next[i] = Math.min(nums[i + 1], next[i + 1]);
        }
        //最后做总计算
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < N - 1; i++){
            if (nums[i] > pre[i] && nums[i] > next[i]){
                ans = Math.min(ans, pre[i] + nums[i] + next[i]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
