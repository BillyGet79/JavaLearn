package class20;

//https://leetcode.cn/problems/longest-palindromic-subsequence/
public class Code01_PalindromeSubsequence {

    public static int lpsl(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    //str[L...R]最长回文子序列长度返回
    public static int f(char[] str, int L, int R){
        if (L == R){    //base case
            return 1;
        }else if (L + 1 == R){
            return str[L] == str[R] ? 2 : 1;
        }else {
            //既不以L开头，也不以R结尾
            int p1 = f(str, L + 1, R - 1);
            //以L为开头，不以R为结尾
            int p2 = f(str, L, R - 1);
            //不以L为开头，以R为结尾
            int p3 = f(str, L + 1, R);
            //既以L开头，也以R为结尾
            int p4 = str[L] == str[R] ? 2 + f(str, L + 1, R - 1) : 0;
            return Math.max(Math.max(p1, p2),Math.max(p3, p4));
        }
    }
    //动态规划版本
    public static int longestPalindromeSubseq(String s){
        if (s == null || s.isEmpty()){
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        //这里注意，由于L <= R，所以数组的下半区间是没有意义的
        //初始化
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        //这里我们按行遍历
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = str[i] == str[i - 1] ? 2 : 1;
        }
        //这里我们需要按照斜线的方向去遍历
        for (int index = 2; index < N; index++){
            int j = index;
            int i = 0;
            while (j < N){
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (str[i] == str[j]){
                    dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
                }
                i++;
                j++;
            }
        }
        return dp[0][str.length - 1];
    }
}
