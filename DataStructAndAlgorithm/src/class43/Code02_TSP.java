package class43;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code02_TSP {

    public static int t1(int[][] matrix) {
        int N = matrix.length; // 0...N-1
        // set
        // set.get(i) != null i这座城市在集合里
        // set.get(i) == null i这座城市不在集合里
        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            set.add(1);
        }
        return func1(matrix, set, 0);
    }
    // 任何两座城市之间的距离，可以在matrix里面拿到
    // set中表示着哪些城市的集合，
    // start这座城一定在set里，
    // 从start出发，要把set中所有的城市过一遍，最终回到0这座城市，最小距离是多少
    public static int func1(int[][] matrix, List<Integer> set, int start) {
        int cityNum = 0;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                cityNum++;
            }
        }
        if (cityNum == 1) {
            return matrix[start][0];
        }
        // cityNum > 1  不只start这一座城
        set.set(start, null);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                // start -> i i... -> 0
                int cur = matrix[start][i] + func1(matrix, set, i);
                min = Math.min(min, cur);
            }
        }
        set.set(start, 1);
        return min;
    }

    //这个解法只是使用了位图，并没有进行缓存
    public static int t2(int[][] matrix) {
        int N = matrix.length;
        int allCity = (1 << N) - 1;
        return func2(matrix, allCity, 0);
    }

    public static int func2(int[][] matrix, int cityStatus, int start) {
        //如果当前只剩下一个有效元素了，那么直接返回当前start到0的距离
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            return matrix[start][0];
        }
        //把start位的1去掉
        cityStatus &= (~(1 << start));
        int min = Integer.MAX_VALUE;
        for (int move = 0; move < matrix.length; move++) {
            if ((cityStatus & (1 << move)) != 0) {
                int cur = matrix[start][move] + func2(matrix, cityStatus, move);
                min = Math.min(min, cur);
            }
        }
        cityStatus |= (1 << start);
        return min;
    }

    //进行缓存处理
    public static int t3(int[][] matrix) {
        int N = matrix.length;
        int allCity = (1 << N) - 1;
        int[][] dp = new int[allCity + 1][N];
        for (int i = 0; i < allCity + 1; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }
        return func3(matrix, allCity, 0, dp);
    }

    public static int func3(int[][] matrix, int cityStatus, int start, int[][] dp) {
        if (dp[cityStatus][start] != -1) {
            return dp[cityStatus][start];
        }
        //如果当前只剩下一个有效元素了，那么直接返回当前start到0的距离
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            return matrix[start][0];
        }
        //把start位的1去掉
        cityStatus &= (~(1 << start));
        int min = Integer.MAX_VALUE;
        for (int move = 0; move < matrix.length; move++) {
            if (move != start && (cityStatus & (1 << move)) != 0) {
                int cur = matrix[start][move] + func3(matrix, cityStatus, move, dp);
                min = Math.min(min, cur);
            }
        }
        cityStatus |= (1 << start);
        dp[cityStatus][start] = min;
        return min;
    }

    public static int t4(int[][] matrix) {
        int N = matrix.length; // 0...N-1
        int statusNums = 1 << N;
        int[][] dp = new int[statusNums][N];

        for (int status = 0; status < statusNums; status++) {
            for (int start = 0; start < N; start++) {
                if ((status & (1 << start)) != 0) {
                    if (status == (status & (~status + 1))) {
                        dp[status][start] = matrix[start][0];
                    } else {
                        int min = Integer.MAX_VALUE;
                        // start 城市在status里去掉之后，的状态
                        int preStatus = status & (~(1 << start));
                        // start -> i
                        for (int i = 0; i < N; i++) {
                            if ((preStatus & (1 << i)) != 0) {
                                int cur = matrix[start][i] + dp[preStatus][i];
                                min = Math.min(min, cur);
                            }
                        }
                        dp[status][start] = min;
                    }
                }
            }
        }
        return dp[statusNums - 1][0];
    }

    public static int[][] generateGraph(int maxSize, int maxValue) {
        int len = (int) (Math.random() * maxSize) + 1;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = (int) (Math.random() * maxValue) + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            matrix[i][i] = 0;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 50;
        System.out.println("测试功能开始");
        for (int i = 0; i < 20000; i++) {
            int[][] matrix = generateGraph(len, value);
            int ans1 = t1(matrix);
            int ans2 = t2(matrix);
            int ans3 = t3(matrix);
            int ans4 = t4(matrix);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
                System.out.println("fuck");
            }
        }
        System.out.println("测试功能结束");
    }

}
