package class05;

public class Code1_CountOfRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper){
        if (nums == null || nums.length == 0){
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return count(sum, 0, nums.length - 1, lower, upper);
    }
    //arr[L, R]已经不传进来了，只传进来sum（前缀和数组）
    //在原始的arr[L...R]中，有多少子数组累加和在[lower, upper]上
    public static int count(long[] sum, int L, int R, int lower, int upper){
        if(L == R){
            //L = R，sum[L]表示的是原nums数组中0~L的和是多少
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        //L != R 时，范围上不止一个位置
        int mid = L + ((R - L) >> 1);
        int leftPart = count(sum, L, mid, lower, upper);
        int rightPart = count(sum, mid + 1, R, lower, upper);
        int merge = merge(sum, L, mid, R, lower, upper);
        return leftPart + rightPart + merge;
    }
    public static int merge(long[] sum, int L, int mid, int R, int lower, int upper){
        //不merge但是，对于右组中的每个数X，求左组中有多少个数，位于[X - upper, X - lower]
        int ans = 0;
        //右组中每一个数是递增的，所以每一个[X - upper, X - lower]的下限和上限都是递增的，这使得遍历时指针不回退
        //定义一个窗口（即L和R两个指针），来保存满足当前右组数满足条件的元素，由上一条可以看出，窗口指针不回退
        int windowL = L;
        int windowR = L;
        //左闭右开的窗口
        for (int i = mid + 1; i <= R; i++) {
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (windowR <= mid && sum[windowR] <= max){
                windowR++;
            }
            while (windowL <= mid && sum[windowR] < min){
                windowL++;
            }
            ans += windowR - windowL;
        }
        //正常merge
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R){
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= mid){
            help[i++] = sum[p1++];
        }
        while (p2 <= R){
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[L + i] = help[i];
        }
        return ans;
    }
}
