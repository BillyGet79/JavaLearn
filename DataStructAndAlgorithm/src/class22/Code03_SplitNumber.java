package class22;

public class Code03_SplitNumber {

    public static int ways(int n){
        if (n < 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        return process(1, n);
    }

    //上一个拆出来的数是Pre
    //当前还剩rest
    private static int process(int pre, int rest) {
        if (rest == 0){
            return 1;
        }
        if (pre > rest){
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++){
            ways += process(i, rest - i);
        }
        return ways;
    }

    public static int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        //行为pre，列为rest
        //由于pre <= rest， 所以这个数组的下半三角是空的，用不上，如果后续遍历到了，直接跳过
        //第一行由于pre=0，而我们没有把0作为拆分的元素，所以第一行没用
        //初始化
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++){
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--){
            for (int rest = pre + 1; rest <= n; rest++){
                int ways = 0;
                for (int i = pre; i <= rest; i++){
                    ways += dp[i][rest - i];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        //行为pre，列为rest
        //由于pre <= rest， 所以这个数组的下半三角是空的，用不上，如果后续遍历到了，直接跳过
        //第一行由于pre=0，而我们没有把0作为拆分的元素，所以第一行没用
        //初始化
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++){
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--){
            for (int rest = pre + 1; rest <= n; rest++){
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int test = 39;
        System.out.println(ways(test));
        System.out.println(dp1(test));
        System.out.println(dp2(test));
    }

}
