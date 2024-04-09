package class41;

public class Code04_SplitArrayLargestSum {

    //我们使用的sum数组的0号位置是空着的，所以这里计算向右移动一个
    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    public int splitArray1(int[] nums, int k) {
        int N = nums.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[N][k + 1];
        //初始化
        for (int j = 1; j <= k; j++) {
            dp[0][j] = nums[0];
        }
        for (int i = 1; i < N; i++) {
            dp[i][1] = sum(sum, 0, i);
        }
        //每一行从上往下
        //每一列从左往右
        for (int i = 1; i < N; i++) {
            for (int j = 2; j <= k; j++) {
                int ans = Integer.MAX_VALUE;
                for (int leftEnd = 0; leftEnd <= i; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
                    if (cur < ans) {
                        ans = cur;
                    }
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][k];
    }

    public int splitArray2(int[] nums, int k) {
        int N = nums.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[N][k + 1];
        int[][] best = new int[N][k + 1];
        for (int j = 1; j <= k; j++) {
            dp[0][j] = nums[0];
            best[0][j] = -1;
        }
        for (int i = 1; i < N; i++) {
            dp[i][1] = sum(sum, 0, i);
            best[i][1] = -1;
        }
        //从第二列开始，从左往右
        //从最后一行开始，从下往上
        for (int j = 2; j <= k; j++) {
            for (int i = N - 1; i >= 1; i--) {
                //记录遍历下端，这里不需要额外判断，这个元素一定存在
                int down = best[i][j - 1];
                //如果最后一行，是没有下方的元素的，这个时候遍历上限就是N - 1
                int up = i == N - 1 ? N - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int bestChoose = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
                    if (cur < ans) {
                        ans = cur;
                        bestChoose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestChoose;
            }
        }
        return dp[N - 1][k];
    }

    //本题的最优解
    public int splitArray(int[] nums, int k) {
        //先算累加和
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        //二分左指针
        long l = 0;
        //二分右指针
        long r = sum;
        //保存答案
        long ans = 0;
        while (l <= r) {
            long mid = (l + r) / 2;
            //得到在当前指标下使用的最少的画匠
            long cur = getNeedParts(nums, mid);
            if (cur <= k) {
                //如果比目标的人少，说明有的优化，向左划分
                ans = mid;
                r = mid - 1;
            } else {
                //如果比目标的人多，说明要求过高，向右划分
                l = mid + 1;
            }
        }
        return (int)ans;
    }

    public static int getNeedParts(int[] nums, long aim) {
        for (int i = 0; i < nums.length; i++) {
            //遍历数组，如果又发现大于aim的，那么直接返回系统最大值
            if (nums[i] > aim) {
                return Integer.MAX_VALUE;
            }
        }
        //至少有一个人
        int parts = 1;
        //记录当前的总和
        int all = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (all + nums[i] > aim) {
                //如果当前的总和加上下一个元素超了，那么就需要再多一个人，并且总和刷新
                parts++;
                all = nums[i];
            } else {
                //如果没有，那么就继续累加
                all += nums[i];
            }
        }
        return parts;
    }
}
