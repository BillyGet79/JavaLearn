package class26;

public class Code03_ZeroLeftOneStringNumber {

    public static int getNum(int n){
        if (n <= 0){
            return 0;
        }
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




    public static void main(String[] args) {
        for (int i = 0; i != 20; i++) {
            System.out.println(getNum(i));
//            System.out.println(getNum1(i));
//            System.out.println(getNum2(i));
//            System.out.println(getNum3(i));
            System.out.println("===================");
        }

    }
}
