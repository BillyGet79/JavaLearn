package class43;

public class Code03_PavingTile {

    public static int ways3(int N, int M) {
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int max = Math.max(N, M);
        int min = Math.min(N, M);
        int pre = (1 << min) - 1;
        int[][] dp = new int[pre + 1][max + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(pre, 0, max, max, dp);
    }

    public static int process3(int pre, int i, int N, int M, int[][] dp) {
        if (dp[pre][i] != -1) {
            return dp[pre][i];
        }
        int ans = 0;
        if (i == N) {
            ans = pre == ((1 << M) - 1) ? -1 : 0;
        } else {
            int op = ((~pre) & ((1 << M) - 1));
            ans = dfs3(op, M - 1, i, N, M, dp);
        }
        dp[pre][i] = ans;
        return ans;
    }

    public static int dfs3(int op, int col, int level, int N, int M, int[][] dp) {
        if (col == -1) {
            return process3(op, level + 1, N, M, dp);
        }
        int ans = 0;
        ans += dfs3(op, col - 1, level, N, M, dp);
        if (col > 0 && (op & (3 << (col - 1))) == 0) {
            ans += dfs3((op | (3 << (col - 1))), col - 2, level, N, M, dp);
        }
        return ans;
    }

}
