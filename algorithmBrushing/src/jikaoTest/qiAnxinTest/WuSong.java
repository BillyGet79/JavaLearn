package jikaoTest.qiAnxinTest;

public class WuSong {

    public static int maxValue(int Hp, int Att, int[] h, int[] a, int[] m) {
        int n = h.length;
        int[][] dp = new int[n + 1][Hp + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Hp; j++) {
                if (j >= a[i - 1]) {    //能够打这只老虎
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - a[i - 1]] + m[i - 1]);
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);    //不打这只老虎
            }
        }
        return dp[n][Hp];
    }
}
