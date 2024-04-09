package class21;

public class Code02_CoinsWayEveryPaperDifferent {

    public static int coinWays(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return aim == 0 ? 1 : 0;
        }
        return process(arr, aim, 0);
    }
    public static int process(int[] arr, int rest, int index){
        if (rest < 0){
            return 0;
        }
        if (index == arr.length){
            return rest == 0 ? 1 : 0;
        }
        //考虑这一张货币
        int p1 = process(arr, rest - arr[index], index + 1);
        //不考虑这张货币
        int p2 = process(arr, rest, index + 1);
        return p1 + p2;
    }

    public static int dp(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return aim == 0 ? 1 : 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        //初始化
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--){
            for (int rest = aim; rest >= 0; rest--){
                int p1 = 0;
                if (rest - arr[index] >= 0){
                    p1 = dp[index + 1][rest - arr[index]];
                }
                int p2 = dp[index + 1][rest];
                dp[index][rest] = p1 + p2;
            }
        }
        return dp[0][aim];
    }


    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
