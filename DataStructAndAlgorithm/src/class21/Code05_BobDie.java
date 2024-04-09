package class21;

public class Code05_BobDie {

    public static double livePosibility1(int N, int M, int row, int col, int k){
        return (double) process(N, M, row, col, k) / Math.pow(4, k);
    }
    private static int process(int n, int m, int row, int col, int k) {
        if (row < 0 || row == n || col < 0 || col == m){
            return 0;
        }
        if (k == 0){
            return 1;
        }
        int up = process(n, m, row, col + 1, k - 1);
        int down = process(n, m, row, col - 1, k - 1);
        int left = process(n, m, row + 1, col, k - 1);
        int right = process(n, m, row - 1, col, k - 1);
        return up + down + left + right;
    }
    public static double livePosibility2(int N, int M, int row, int col, int k){
        int[][][] dp = new int[N][M][k + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++){
            for (int i = 0; i < N; i++){
                for (int j = 0; j < M; j++){
                    dp[i][j][rest] += pick(dp, N, M, i, j + 1, rest - 1);
                    dp[i][j][rest] += pick(dp, N, M, i, j - 1, rest - 1);
                    dp[i][j][rest] += pick(dp, N, M, i + 1, j, rest - 1);
                    dp[i][j][rest] += pick(dp, N, M, i - 1, j, rest - 1);
                }
            }
        }
        return dp[row][col][k] / Math.pow(4, k);
    }
    public static double livePosibility3(int N ,int M, int row, int col, int k){
        int[][] now = new int[N][M];
        int[][] next = new int[N][M];
        for (int i = 0; i < now.length; i++) {
            for (int j = 0; j < now[i].length; j++) {
                now[i][j] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    next[i][j] = pick2(now, N, M, i, j + 1);
                    next[i][j] += pick2(now, N, M, i, j - 1);
                    next[i][j] += pick2(now, N, M, i + 1, j);
                    next[i][j] += pick2(now, N, M, i - 1, j);
                }
            }
            int[][] temp = now;
            now = next;
            next = temp;
        }
        //因为在弹出循环的时候，next和now做了交换，所以now保存着最后的答案
        return now[row][col] / Math.pow(4, k);
    }

    public static int pick2(int[][] now, int N, int M, int row, int col){
        if (row < 0 || row == N || col < 0 || col == M){
            return 0;
        }
        return now[row][col];
    }

    public static int pick(int[][][] dp ,int N ,int M, int i, int j, int rest){
        if (i < 0 || i == N || j < 0 || j == M){
            return 0;
        }
        return dp[i][j][rest];
    }
    public static void main(String[] args) {
        System.out.println(livePosibility1(50, 50, 6, 6, 10));
        System.out.println(livePosibility2(50, 50, 6, 6, 10));
        System.out.println(livePosibility3(50, 50, 6, 6, 10));
    }
}
