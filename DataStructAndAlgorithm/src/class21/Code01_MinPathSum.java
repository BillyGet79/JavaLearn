package class21;

public class Code01_MinPathSum {

    public static int minPathSum1(int[][] matrix) {
        return process(matrix, 0, 0);
    }

    public static int process(int[][] matrix, int x, int y) {
        if (x == matrix.length - 1 && y == matrix[0].length - 1) {
            return matrix[x][y];
        }
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        if (x != matrix.length - 1) {
            p1 = matrix[x][y] + process(matrix, x + 1, y);
        }
        if (y != matrix[0].length - 1) {
            p2 = matrix[x][y] + process(matrix, x, y + 1);

        }
        return Math.min(p1, p2);
    }

    public static int minPathSum2(int[][] m) {
        int row = m.length; //行
        int col = m[0].length;  //列
        //先处理最后一行
        for (int y = m[0].length - 2; y >= 0; y--){
            m[row - 1][y] += m[row - 1][y + 1];
        }
        for (int x = m.length - 2; x >= 0; x--){
            m[x][col - 1] += m[x + 1][col - 1];
        }
        for (int x = m.length - 2; x >= 0; x--){
            for (int y = m[x].length - 2; y >= 0; y--){
                m[x][y] += Math.min(m[x + 1][y], m[x][y + 1]);
            }
        }
        return m[0][0];
    }

    public static int minPathSum3(int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        int[] dp = new int[col];
        dp[col - 1] = matrix[row - 1][col - 1];
        for (int i = dp.length - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] + matrix[row - 1][i];
        }
        for (int x = row - 2; x >= 0; x--){
            dp[col - 1] = dp[col - 1] + matrix[x][col - 1];
            for (int y = col - 2; y >= 0; y--){
                dp[y] = Math.min(dp[y], dp[y + 1]) + matrix[x][y];
            }
        }
        return dp[0];
    }

    //对数器
    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum3(m));
        System.out.println(minPathSum2(m));
    }
}
