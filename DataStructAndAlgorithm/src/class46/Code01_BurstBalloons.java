package class46;

public class Code01_BurstBalloons {

    //暴力递归方式实现
    public int maxCoins0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int N = nums.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = nums[i];
        }
        help[N + 1] = 1;
        return process(help, 1, N);
    }
    //L-1位置和R+1位置永远不越界，而且这两个位置的气球一定没有爆
    //返回区间最大得分
    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L - 1] * arr[L] * arr[R + 1];
        }
        //尝试每一种情况，最后打爆的气球是什么位置
        //第一种可能性
        int p1 = process(arr, L + 1, R) + arr[L - 1] * arr[L] * arr[R + 1];
        //第二种可能性
        int p2 = process(arr, L, R - 1) + arr[L - 1] * arr[R] * arr[R + 1];
        //取最大值
        int ans = Math.max(p1, p2);
        //其余的可能性
        for (int i = L + 1; i <= R - 1; i++) {
            int left = process(arr, L, i - 1);
            int right = process(arr, i + 1, R);
            int last = arr[L - 1] * arr[i] * arr[R + 1];
            int cur = last + left + right;
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    //直接实现严格元素依赖的动态规划版本
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int N = nums.length;
        int[] arr = new int[N + 2];
        arr[0] = 1;
        for (int i = 0; i < N; i++) {
            arr[i + 1] = nums[i];
        }
        arr[N + 1] = 1;
        int[][] dp = new int[N + 2][N + 2];
        //base case
        for (int i = 1; i <= N; i++) {
            dp[i][i] = arr[i - 1] * arr[i] * arr[i + 1];
        }
        //进行动态规划计算
        //这里我们要注意，L一定小于等于R，所以这个数组的下三角部分的值是无效的
        for (int L = N; L >= 1; L--) {
            for (int R = L + 1; R <= N; R++) {
                int p1 = dp[L + 1][R] + arr[L - 1] * arr[L] * arr[R + 1];
                //第二种可能性
                int p2 = dp[L][R - 1] + arr[L - 1] * arr[R] * arr[R + 1];
                //取最大值
                int ans = Math.max(p1, p2);
                //其余的可能性
                for (int i = L + 1; i <= R - 1; i++) {
                    int left = dp[L][i - 1];
                    int right = dp[i + 1][R];
                    int last = arr[L - 1] * arr[i] * arr[R + 1];
                    int cur = last + left + right;
                    ans = Math.max(ans, cur);
                }
                dp[L][R] = ans;
            }
        }
        return dp[1][N];
    }


}
