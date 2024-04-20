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
    public static int[] getMaxMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int row = matrix.length;
        int col = matrix[0].length;

    }
}
