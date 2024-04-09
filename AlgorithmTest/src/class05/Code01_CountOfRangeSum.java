package class05;

import java.util.LinkedList;

public class Code01_CountOfRangeSum {

    //https://leetcode.cn/problems/count-of-range-sum/
    //↑在这里测试
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            sum[i] = nums[i] + sum[i - 1];
        }
        return count(sum, 0, nums.length - 1, upper, lower);
    }

    public static int count(long[] sum, int l, int r, int upper, int lower){
        if (l == r){
            return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
        }
        int mid = l + ((r - l) >> 1);
        int leftPart = count(sum, l, mid, upper, lower);
        int rightPart = count(sum , mid + 1, r, upper, lower);
        int merge = merge(sum, l, mid, r, upper, lower);
        return leftPart + rightPart + merge;
    }

    public static int merge(long[] sum, int l, int mid, int r, int upper, int lower){
        int ans = 0;
        int windowL = l;
        int windowR = l;
        for (int i = mid + 1; i <= r; i++){
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while(windowR <= mid && sum[windowR] <= max){
                windowR++;
            }
            while(windowL <= mid && sum[windowL] < min){
                windowL++;
            }
            ans += windowR - windowL;
        }
        long[] help = new long[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while(p1 <= mid && p2 <= r){
            help[i++] = sum[p1] < sum[p2] ? sum[p1++] : sum[p2++];
        }
        while(p1 <= mid){
            help[i++] = sum[p1++];
        }
        while(p2 <= r){
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++){
            sum[l + i] = help[i];
        }
        return ans;
    }
}
