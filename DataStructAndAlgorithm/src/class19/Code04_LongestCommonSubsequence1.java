package class19;

public class Code04_LongestCommonSubsequence1 {

    public static int longestCommonSubsequence1(String s1, String s2){
        if (s1 == null || s2 == null || s1.isEmpty() | s2.isEmpty()){
            return 0;
        }
        char[] str1 =s1.toCharArray();
        char[] str2 =s2.toCharArray();
        //尝试
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    //str1[0...i]与str2[0...j]最长公共子序列多长？
    //返回
    public static int process1(char[] str1, char[] str2, int i, int j){
        if (i == 0 && j == 0){
            return str1[i] == str2[j] ? 1 : 0;
        }
        //当str1只有一个字符的情况下
        if (i == 0){
            if (str1[i] == str2[j]){
                //如果与str2[j](末尾位置)相等，则返回1
                return 1;
            }else {
                //如果不相等，则去看剩下的
                return process1(str1, str2, i, j - 1);
            }
        }else if (j == 0) {
            if (str1[i] == str2[j]){
                //如果与str1[i](末尾位置)相等，则返回1
                return 1;
            }else {
                //如果不相等，则去看剩下的
                return process1(str1, str2, i - 1, j);
            }
        } else {
            int p1 = process1(str1, str2, i - 1, j);
            int p2 = process1(str1, str2, i, j - 1);
            int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(Math.max(p1, p2), p3);
        }
    }

    public static int longestCommonSubsequence2(String s1, String s2){
        if (s1 == null || s2 == null || s1.isEmpty() | s2.isEmpty()){
            return 0;
        }
        char[] str1 =s1.toCharArray();
        char[] str2 =s2.toCharArray();
        int M = str1.length;
        int N = str2.length;
        int[][] dp = new int[M][N];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < N; i++){
            if (str1[0] == str2[i]){
                dp[0][i] = 1;
            }else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 1; i < M; i++){
            if (str2[0] == str1[i]){
                dp[i][0] = 1;
            }else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < M; i ++){
            for (int j = 1; j < N; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : Integer.MIN_VALUE;
                dp[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }
        return dp[M - 1][N - 1];
    }
}
