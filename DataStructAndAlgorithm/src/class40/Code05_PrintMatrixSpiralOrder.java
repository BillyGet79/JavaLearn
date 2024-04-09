package class40;

public class Code05_PrintMatrixSpiralOrder {
    public static void spiralOrderPrint(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
        //如果遍历圈到最后两个点的横坐标相等，那就直接将其从左往右打印即可
        if (tR == dR) {
            for (int i = tC; i <= dC; i++) {
                System.out.print(m[tR][i] + " ");
            }
        } else if (tC == dC) {  //这里与上面同理
            for (int i = tR; i <= dR; i++) {
                System.out.print(m[i][tC] + " ");
            }
        } else {
            int curC = tC;
            int curR = tR;
            //先打印上边界
            while (curC != dC) {
                System.out.print(m[tR][curC] + " ");
                curC++;
            }
            //打印右边界
            while (curR != dR) {
                System.out.print(m[curR][dC] + " ");
                curR++;
            }
            //打印下边界
            while (curC != tC) {
                System.out.print(m[dR][curC] + " ");
                curC--;
            }
            //打印左边界
            while (curR != tR) {
                System.out.print(m[curR][tC] + " ");
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);

    }
}
