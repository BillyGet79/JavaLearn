package class26;

import java.util.Arrays;

public class Code02_FibonacciProblem {

    //暴力递归
    public static int f1(int n){
        if (n == 1 || n == 2){
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }
    //对动态规划的优化版本
    //不难发现在动归迭代的计算中，前面的元素其实已经不会再用到，所以可以进行空间压缩
    public static int f2(int n){
        if (n == 1 || n == 2){
            return 1;
        }
        int prepre = 1;
        int pre = 1;
        int ans = 0;
        for (int i = 3; i <= n; i++){
            ans = pre + prepre;
            prepre = pre;
            pre = ans;
        }
        return ans;
    }
    //矩阵快速幂的方法实现
    public static int f3(int n){
        if (n == 1 || n == 2){
            return 1;
        }
        int[][] base = new int[][]{
                {1,1},
                {1,0}
        };
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }
    //矩阵快速幂运算
    public static int[][] matrixPower(int[][] m, int k){
        int[][] res = new int[m.length][m[0].length];
        //注意这里，要初始化为单位矩阵，而不是全是1的矩阵
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = m;
        while (k != 0){
            if ((k & 1) == 1){
                res = product(res, t);
            }
            t = product(t, t);
            k >>= 1;
        }
        return res;
    }

    //矩阵相乘
    public static int[][] product(int[][] m, int[][] n){
        int row = m.length; //所得矩阵的行
        int col = n[0].length;  //所得矩阵的列
        int[][] res = new int[row][col];
        int k = m[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int c = 0; c < k; c++) {
                    res[i][j] += m[i][c] * n[c][j];
                }
            }
        }
        return res;
    }

    //暴力递归
    public static int c1(int n){
        if (n == 1 || n == 2 || n == 3){
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    //动态规划空间压缩
    public static int c2(int n){
        if (n == 1 || n == 2 || n == 3){
            return n;
        }
        int p1 = 1;
        int p2 = 2;
        int p3 = 3;
        int ans = 0;
        for (int i = 4; i <= n; i++){
            ans = p3 + p1;
            p1 = p2;
            p2 = p3;
            p3 = ans;
        }
        return ans;
    }

    //矩阵快速幂算法
    public static int c3(int n){
        if (n == 1 || n == 2 || n == 3){
            return n;
        }
        int[][] base = new int[][]{
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

    //暴力递归
    public static int s1(int n){
        if (n == 1 || n == 2){
            return n;
        }
        return s1(n - 1) + s1(n - 2);
    }

    //动态规划空间压缩
    public static int s2(int n){
        if (n == 1 || n == 2){
            return n;
        }
        int p1 = 1;
        int p2 = 2;
        int ans = 0;
        for (int i = 3; i <= n; i++){
            ans = p1 + p2;
            p1 = p2;
            p2 = ans;
        }
        return ans;
    }

    //快速矩阵幂
    public static int s3(int n){
        if (n == 1 || n == 2){
            return n;
        }
        int[][] base = new int[][]{
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public static void main(String[] args) {
        int n = 19;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
        System.out.println("===");

        System.out.println(s1(n));
        System.out.println(s2(n));
        System.out.println(s3(n));
        System.out.println("===");

        System.out.println(c1(n));
        System.out.println(c2(n));
        System.out.println(c3(n));
        System.out.println("===");

    }
}
