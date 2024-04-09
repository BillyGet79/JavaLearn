package class47;

public class Code01_StrangePrinter {

    //暴力递归方法
    //毫无意外的超时
    public int strangePrinter1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process1(str, 0, str.length - 1);
    }

    public static int process1(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        //假设每一个字符都是单独一次打印出来的
        int ans = R - L + 1;
        //分情况讨论
        for (int k = L + 1; k <= R; k++) {
            //注意最后减去的部分
            ans = Math.min(ans, process1(str, L, k - 1) + process1(str, k, R) - (str[L] == str[k] ? 1 : 0));
        }
        return ans;
    }

    public int strangePrinter(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        //初始化
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            //如果两个元素不相同，则必须打印两次
            dp[i][i + 1] = str[i] == str[i + 1] ? 1 : 2;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = R - L + 1;
                for (int k = L + 1; k <= R; k++) {
                    dp[L][R] = Math.min(dp[L][R], dp[L][k - 1] + dp[k][R] - (str[L] == str[k] ? 1 : 0));
                }
            }
        }
        return dp[0][N - 1];
    }
}
