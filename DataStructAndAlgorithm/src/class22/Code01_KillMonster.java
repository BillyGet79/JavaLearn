package class22;

public class Code01_KillMonster {

    //怪兽有N滴血
    //随机范围为0~M
    //砍K次
    public static double right(int N, int M, int K){
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long)Math.pow(M + 1, K);
        long kill = process(N, M, K);
        return (double)kill / (double) all;
    }

    //怪兽当前还剩hp的血
    //砍一次随机伤害M
    //还剩rest次可以砍
    public static long process(int hp, int M, int rest){
        if (rest == 0){
            return hp <= 0 ? 1 : 0;
        }
        //思考一下这一步
        //如果当我们发现血量小于0了，那么后面的所有步数都算是成功的方案，直接把这些方案全部算作依次返回即可，没必要进行递归了
        if (hp <= 0){
            return (long)Math.pow(M + 1, rest);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++){
            ways += process(hp - i, M, rest - 1);
        }
        return ways;
    }

    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        //初始化
        dp[0][0] = 1;   //第0行剩下位置都为0
        for (int rest = 1; rest <= K; rest++){
            dp[rest][0] = (long) Math.pow(M + 1, rest);
            for (int hp = 1; hp <= N; hp++){
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0){
                        ways += dp[rest - 1][hp - i];
                    }else {
                        //一定要这样考虑，只有这样才能把hp < 0的情况考虑到
                        ways += (long) Math.pow(M + 1, rest - 1);
                    }
                }
                dp[rest][hp] = ways;
            }
        }
        return (double) dp[K][N] / (double) all;
    }

    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        //初始化
        dp[0][0] = 1;   //第0行剩下位置都为0
        for (int rest = 1; rest <= K; rest++){
            dp[rest][0] = (long) Math.pow(M + 1, rest);
            for (int hp = 1; hp <= N; hp++){
                dp[rest][hp] = dp[rest - 1][hp] + dp[rest][hp - 1];
                if (hp - 1 - M >= 0){
                    dp[rest][hp] -= dp[rest - 1][hp - 1 - M];
                }else {
                    dp[rest][hp] -= (long) Math.pow(M + 1, rest - 1);
                }
            }
        }
        return (double) dp[K][N] / (double) all;
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                System.out.println(N + " " + M + " " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
