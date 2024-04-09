package class18;

public class Code01_RobotWalk {

    public static int way1(int N, int start, int aim, int K){
        return process1(start, K, aim, N);
    }

    //机器人当前来到的位置是cur
    //机器人还有rest步需要去走
    //最终的目标是aim
    //返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
    public static int process1(int cur, int rest, int aim ,int N){
        if (rest == 0){ //base case
            return cur == aim ? 1 : 0;
        }
        //rest > 0，还有步数要走
        if (cur == 1){
            return process1(2, rest - 1, aim, N);
        }
        if (cur == N){
            return process1(N - 1, rest - 1, aim, N);
        }
        return process1(cur + 1, rest - 1, aim, N) + process1(cur - 1, rest - 1, aim, N);
    }

    public static int way2(int N, int start, int aim, int K){
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        //dp就是缓存表
        //process2(cur, rest)之前没算过！dp[cur][rest] == -1
        //dp[cur][rest] != -1，那么说明process2(cur, rest)算过，其值就为该方法调用的返回值
        return process2(start, K, aim, N, dp);
    }

    public static int process2(int cur, int rest, int aim ,int N, int[][] dp){
        if (dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0){ //base case
            ans = cur == aim ? 1 : 0;
        }else if (cur == 1){
            ans = process2(2, rest - 1, aim, N, dp);
        }else if (cur == N){
            ans =  process2(N - 1, rest - 1, aim, N, dp);
        }else {
            ans = process2(cur + 1, rest - 1, aim, N, dp) + process2(cur - 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    public static int way3(int N, int start, int aim, int K){
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1; //java中初始化的数组数值全是0，所以只需要改这一个值就可以了，不过C/C++必须要将这一列进行初始化
        //先遍历列再遍历行
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur <= N - 1; cur++){
                dp[cur][rest] = dp[cur + 1][rest - 1] + dp[cur - 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(way1(4, 2, 4, 4));
        System.out.println(way2(4, 2, 4, 4));
        System.out.println(way3(4, 2, 4, 4));
    }


}
