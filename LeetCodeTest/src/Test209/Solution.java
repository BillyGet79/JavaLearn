package Test209;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/9
 * @description TODO
 */
public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        //先求出前缀和数组，通过前缀和数组求解
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int res = Integer.MAX_VALUE;
        //滑动窗口
        int left = 0;
        int right = 0;
        while (right < preSum.length) {
            if (preSum[right] - preSum[left] < target) {
                right++;
            } else {
                res = Math.min(res, right - left);
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }
}
