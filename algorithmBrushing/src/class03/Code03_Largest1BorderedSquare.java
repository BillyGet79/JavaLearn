package class03;

/**
 * 题目3
 * 给定一个只有0和1组成的二维数组
 * 返回边框全是1的最大正方形面积
 * 本题测试链接 : <a href="https://leetcode.cn/problems/largest-1-bordered-square/">...</a>
 */
public class Code03_Largest1BorderedSquare {

    //首先，我们遍历所有可能的正方形，这个过程的时间复杂度为O(N^3)
    //而对于我们遍历到的正方形，我们要判断其边框是否都为1
    //这个过程我们需要在O(1)的时间复杂度下实现，否则使用遍历我们的时间复杂度就会变成O(N^4)
    //所以我们需要对二维数组进行预处理

    //对于边框判断，我们可以生成两个二维数组，里面分别存储每个点包括自己在内的右边和下边有多少个连续的1
    //这个计算过程我们可以通过从后往前判断得出

    //时间复杂度O(N^3)
    public static int test(int[][] m) {
        int N = m.length;
        int M = m[0].length;

        //遍历代价O(n^2)
        int[][] right = new int[N][M];
        int[][] down = new int[N][M];
        setBorderMap(m, right, down);

        int ans = 0;
        //O(n^3)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int border = 1; border <= Math.min(N - i, M - j); border++) {
                    //该正方形左上顶点(i, j)，边长为border
                    //验证这个正方形，是不是边框都是1的 O(1)
                    if (right[i][j] >= border && down[i][j] >= border && right[i + border - 1][j] >= border && down[i][j + border - 1] >= border) {
                        ans = Math.max(border * border, ans);
                    }
                }
            }
        }
        return ans;
    }

    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int N = m.length;
        int M = m[0].length;

        //right数组处理
        for (int i = 0; i < N; i++) {
            right[i][M - 1] = m[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                right[i][j] = m[i][j] == 0 ? 0 : right[i][j + 1] + 1;
            }
        }
        //down数组处理
        for (int j = 0; j < M; j++) {
            down[N - 1][j] = m[N - 1][j];
            for (int i = N - 2; i >= 0; i--) {
                down[i][j] = m[i][j] == 0 ? 0 : down[i + 1][j] + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1,1,1},{1,0,1},{1,1,1}};
        System.out.println(test(m));
    }

}
