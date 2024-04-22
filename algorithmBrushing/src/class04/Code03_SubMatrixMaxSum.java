package class04;

/**
 * 题目3
 * 返回一个二维数组中，子矩阵最大累加和
 * 本题测试链接 : <a href="https://leetcode-cn.com/problems/max-submatrix-lcci/">...</a>
 * 不过这个测试链接是找到这个矩阵的四个坐标，其实是一样的
 */
public class Code03_SubMatrixMaxSum {

    /**
     * 与上一题有异曲同工之处
     * 只不过现在我们扩展到了二维平面
     * 整体的大流程是这样的：
     *  我们先找包含0-0行所有矩形的最大累加和是多少
     *  然后我们再找包含0-1行所有矩形的最大累加和是多少
     *  以此类推即可
     *  然后求1-1行，1-2行，......
     * 不过我们需要进行数组压缩，我们需要通过前缀累加和求出每一列0-n行的累加和是多少
     */

    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        //两个for循环
        //i行到j行进行处理
        for (int i = 0; i < N; i++) {
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                cur = 0;
                for (int k = 0; k < M; k++) {
                    //遍历到当前位置后，就在s数组中加上这个数据
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }


    public static int[] getMaxMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] ans = new int[]{0, 0, 0, 0};
        for (int i = 0; i < N; i++) {
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                cur = 0;
                int begin = 0;
                for (int k = 0; k < M; k++) {
                    s[k] += matrix[j][k];
                    cur += s[k];
                    if (max < cur) {
                        max = cur;
                        ans[0] = i;
                        ans[1] = begin;
                        ans[2] = j;
                        ans[3] = k;
                    }
                    if (cur < 0) {
                        cur = 0;
                        begin = k + 1;
                    }
                }
            }
        }
        return ans;
    }
}
