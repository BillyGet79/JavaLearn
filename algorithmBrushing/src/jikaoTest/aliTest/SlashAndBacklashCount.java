package jikaoTest.aliTest;

import java.io.*;

public class SlashAndBacklashCount {


    public static int[] getSlashAndBacklashCount(boolean[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{0, 0};
        }
        int[] ans = new int[2];
        int N = matrix.length;
        //先进行正斜线遍历
        //上半正斜遍历
        for (int i = 0; i < N; i++) {
            int n = i;
            int m = 0;
            while (n >= 0) {
                if (matrix[n][m]) {
                    ans[0]++;
                    break;
                }
                n--;
                m++;
            }
        }
        //下半正斜遍历
        for (int i = N - 1; i > 0; i--) {
            int n = i;
            int m = N - 1;
            while (n <= N - 1) {
                if (matrix[n][m]) {
                    ans[0]++;
                    break;
                }
                n++;
                m--;
            }
        }
        //然后进行反斜线遍历
        //先进行上三角遍历
        for (int i = N - 1; i >= 0; i--) {
            int n = 0;
            int m = i;
            while (m <= N - 1) {
                if (matrix[n][m]) {
                    ans[1]++;
                    break;
                }
                n++;
                m++;
            }
        }
        //然后进行下三角遍历
        for (int i = N - 1; i > 0; i--) {
            int n = i;
            int m = 0;
            while (n <= N - 1) {
                if (matrix[n][m]) {
                    ans[1]++;
                    break;
                }
                n++;
                m++;
            }
        }
        return ans;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        //先获取矩阵大小
        in.nextToken();
        int N = (int) in.nval;
        //建立矩阵
        //因为格子只有黑白两种颜色，所以我们用布尔矩阵
        boolean[][] matrix = new boolean[N][N];
        //获取黑色格子数量
        in.nextToken();
        int M = (int) in.nval;
        //获取黑色格子在哪里
        for (int i = 0; i < M; i++) {
            //获取行
            in.nextToken();
            int row = (int) in.nval;
            //获取列
            in.nextToken();
            int col = (int) in.nval;
            matrix[row - 1][col - 1] = true;
        }
        int[] ans = getSlashAndBacklashCount(matrix);
        out.println(ans[0] + " " + ans[1]);
        out.flush();
    }
}
