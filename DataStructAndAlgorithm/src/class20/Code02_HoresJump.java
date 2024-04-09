package class20;

public class Code02_HoresJump {

    //当前来到的位置是(x,y)
    //还剩下rest步需要跳
    //跳完rest步，正好跳到a,b的方法数是多少？
    //10 * 9
    public static int process(int x, int y, int rest, int a, int b){
        if (x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }
        if (rest == 0){
            return (x == a && y == b) ? 1 : 0;
        }
        int p1 = process(x + 2, y + 1, rest - 1, a, b);
        int p2 = process(x + 1, y + 2, rest - 1, a, b);
        int p3 = process(x - 1, y + 2, rest - 1, a, b);
        int p4 = process(x - 2, y + 1, rest - 1, a, b);
        int p5 = process(x - 2, y - 1, rest - 1, a, b);
        int p6 = process(x - 1, y - 2, rest - 1, a, b);
        int p7 = process(x + 1, y - 2, rest - 1, a, b);
        int p8 = process(x + 2, y - 1, rest - 1, a, b);
        return p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
    }

    public static int jump(int a, int b, int k){
        return process(0, 0, k, a, b);
    }


    //动态规划实现
    public static int dp(int a, int b, int k){
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        //先遍历rest
        for (int rest = 1; rest <= k; rest++){
            for (int x = 0; x < 10; x++){
                for (int y = 0; y < 9; y++){
                    dp[x][y][rest] += pick(dp,x + 2, y + 1, rest - 1);
                    dp[x][y][rest] += pick(dp,x + 1, y + 2, rest - 1);
                    dp[x][y][rest] += pick(dp,x - 1, y + 2, rest - 1);
                    dp[x][y][rest] += pick(dp,x - 2, y + 1, rest - 1);
                    dp[x][y][rest] += pick(dp,x - 2 ,y - 1, rest - 1);
                    dp[x][y][rest] += pick(dp,x - 1, y - 2, rest - 1);
                    dp[x][y][rest] += pick(dp,x + 1, y - 2, rest - 1);
                    dp[x][y][rest] += pick(dp,x + 2, y - 1, rest - 1);
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int x, int y, int rest){
        if (x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }
        return dp[x][y][rest];
    }




    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
//        System.out.println(ways(x, y, step));
        System.out.println(dp(x, y, step));

        System.out.println(jump(x, y, step));
    }
}
