package class01;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目5
 * 给定一个二维数组matrix
 * 你可以从任何位置出发，走向上下左右四个方向
 * 返回能走出来的最长的递增链长度
 */
public class Code05_LongestIncreasingPath {

    //使用最暴力的尝试方法（就是尝试思想，这个思想一定要刻在心里面，这是任何一个递归方法需要考虑的东西）
    public static int longestIncreasingPath1(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    //从matrix[i][j]开始走，走出来的最长递增链，返回
    public static int process1(int[][] matrix, int i, int j) {
        //(i, j)不越界
        int up = i > 0 && matrix[i][j] < matrix[i - 1][j] ? process1(matrix, i - 1, j) : 0;
        int down = i < (matrix.length - 1) && matrix[i][j] < matrix[i + 1][j] ? process1(matrix, i + 1, j) : 0;
        int left = j > 0 && matrix[i][j] < matrix[i][j - 1] ? process1(matrix, i, j - 1) : 0;
        int right = j < (matrix[0].length - 1) && matrix[i][j] < matrix[i][j + 1] ? process1(matrix, i, j + 1) : 0;

        //返回四个之中最大的，最后一定得注意要+1
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    //用记忆化搜索方法
    public static int longestIncreasingPath2(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process2(matrix, i, j, dp));
            }
        }
        return ans;
    }

    public static int process2(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        //(i, j)不越界
        int up = i > 0 && matrix[i][j] < matrix[i - 1][j] ? process2(matrix, i - 1, j, dp) : 0;
        int down = i < (matrix.length - 1) && matrix[i][j] < matrix[i + 1][j] ? process2(matrix, i + 1, j, dp) : 0;
        int left = j > 0 && matrix[i][j] < matrix[i][j - 1] ? process2(matrix, i, j - 1, dp) : 0;
        int right = j < (matrix[0].length - 1) && matrix[i][j] < matrix[i][j + 1] ? process2(matrix, i, j + 1, dp) : 0;

        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }


}
