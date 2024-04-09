package class46;

public class Code02_RemoveBoxes {

    //第一个版本，暴力递归方法
    //这个显然超时
    public int removeBoxes0(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
        return process0(boxes, 0, boxes.length - 1, 0);
    }
    public static int process0(int[] arr, int L, int R, int K) {
        if (L > R) {
            return 0;
        }
        int ans = process0(arr, L + 1, R, 0) + (K + 1) * (K + 1);
        for (int i = L + 1; i <= R; i++) {
            if (arr[i] == arr[L]) {
                ans = Math.max(ans, process0(arr, L + 1, i - 1, 0) + process0(arr, i, R, K + 1));
            }
        }
        return ans;
    }

    //记忆化搜索版本
    public int removeBoxes1(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return process1(boxes, 0, N - 1, 0, dp);
    }

    public static int process1(int[] arr, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }
        int ans = process1(arr, L + 1, R, 0, dp) + (K + 1) * (K + 1);
        for (int i = L + 1; i <= R; i++) {
            if (arr[i] == arr[L]) {
                ans = Math.max(ans, process1(arr, L + 1, i - 1, 0, dp) + process1(arr, i, R, K + 1, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }

    //优化后的记忆化搜索版本
    public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return process2(boxes, 0, N - 1, 0, dp);
    }

    public static int process2(int[] arr, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }
        //找到最后一个与arr[L]相同的元素
        int last = L;
        while (last + 1 <= R && arr[last + 1] == arr[L]) {
            last++;
        }
        //pre为现在找到的与arr[L]值相同的个数
        int pre = K + last - L;
        //第一种可能性
        int ans = (pre + 1) * (pre + 1) + process2(arr, last + 1, R, 0, dp);
        for (int i = last + 2; i <= R; i++) {
            //必须保证自己前一个元素的值不与arr[L]相同，这样能避免无意义的调用
            if (arr[i] == arr[L] && arr[i - 1] != arr[L]) {
                ans = Math.max(ans, process2(arr, last + 1, i - 1, 0, dp) + process2(arr, i, R, pre + 1, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }
}
