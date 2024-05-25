package jikaoTest.honorTest;

import java.io.*;

public class RunAway {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        int M = (int) in.nval;
        in.nextToken();
        int D = (int) in.nval;
        in.nextToken();
        int T = (int) in.nval;

        boolean ans = tryRunAway(M, D, T);
        if (ans) {
            out.println("YES");
        } else {
            out.println("NO");
        }
        out.flush();
    }

    public static boolean tryRunAway2(int M, int D, int T) {
        return process(M, D, T);
    }

    /**
     *
     * @param m 当前法力值
     * @param length    当前剩余距离
     * @param t 当前剩余时间
     * @return
     */
    public static boolean process(int m, int length, int t) {
        if (t == 0) {
            return length <= 0;
        }
        //第一种情况，当前不使用技能，也不停留
        boolean ans1 = process(m, length - 17, t - 1);
        //第二种情况，当前使用技能
        boolean ans2 = false;
        if (m >= 10) {
            ans2 = process(m - 10, length - 60, t - 1);
        }
        //第三种情况，停留在原地
        boolean ans3 = process(m + 4, length, t - 1);
        //这三种情况，有一种为true，则能够成功逃脱
        return ans1 || ans2 || ans3;
    }

    //动态规划解法
    public static boolean tryRunAway(int M, int D, int T) {
        boolean[][][] dp = new boolean[M + T * 4 + 1][D + 1][T + 1];
        //base case
        int maxM = M + T * 4;
        for (int i = 0; i <= maxM; i++) {
            dp[i][0][0] = true;
        }
        for (int t = 1; t <= T; t++) {
            for (int length = 0; length <= D; length++) {
                for (int m = 0; m <= maxM; m++) {
                    boolean ans2 = false;
                    if (m >= 10) {
                        if (length - 60 < 0) {
                            ans2 = true;
                        } else {
                            ans2 = dp[m - 10][length - 60][t - 1];
                        }
                    }
                    boolean ans3 = dp[m + 4][length][t - 1];
                    boolean ans1 = false;
                    if (length - 17 < 0) {
                        ans1 = true;
                    } else {
                        ans1 = dp[m][length - 17][t - 1];
                    }
                    dp[m][length][t] = ans1 || ans2 || ans3;
                }
            }
        }
        return dp[M][D][T];
    }
}
