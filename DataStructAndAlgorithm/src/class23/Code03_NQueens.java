package class23;

public class Code03_NQueens {

    public static int num1(int n){
        if (n < 1){
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }
    //当前来到i行，一共是0~N-1行
    //在i行上放皇后，所有列都尝试
    //必须要保证跟之前所有的皇后不打架
    //int[] record  record[x] = y   之前的第x行的皇后，放在了y列上
    //返回：不关心i以上发生了什么，i后续有多少合法的方法数
    public static int process1(int i, int[] record, int n){
        if (i == n){
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)){
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }
    public static boolean isValid(int[] record, int i, int j){
        for (int k = 0; k < i; k++){
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)){
                return false;
            }
        }
        return true;
    }
    public static int num2(int n){
        if (n < 1 || n > 32){
            return 0;
        }
        //-1的补码表示为全1
        //limit为1的位置即为有效位置
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }
    //colLim、leftDiaLim、rightDiaLim中为1的位置是我们限制不可以放皇后的位置
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim){
        if (colLim == limit){
            return 1;
        }
        //pos中所有是1的位置，是你可以去尝试得到的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        //尝试所有的可能，计算方法数
        while (pos != 0){
            //获取最右侧的1位置
            mostRightOne = pos & (~pos + 1);
            //将最右侧的1删除掉
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }
    public static void main(String[] args) {
        int n = 15;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
