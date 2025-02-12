package class21;

import javax.swing.*;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

public class Code04_CoinsWaySameValueSamePapper {

    public static class Info{
        public int[] coins;
        public int[] zhangs;
        public int counts;
        public Info(int[] arr){
            HashMap<Integer, Integer> counts = new HashMap<>();
            for (int value : arr){
                if (!counts.containsKey(value)){
                    counts.put(value, 1);
                }else {
                    counts.put(value, counts.get(value) + 1);
                }
            }
            int N = counts.size();
            this.coins = new int[N];
            this.zhangs = new int[N];
            int index = 0;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()){
                coins[index] = entry.getKey();
                zhangs[index++] = entry.getValue();
            }
            this.counts = index;
        }
    }

    public static int coinsWay(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        Info info = new Info(arr);
        return process(info, aim, 0);
    }

    private static int process(Info info, int rest, int index) {
        if (rest < 0){
            return 0;
        }
        if (index == info.counts){
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int i = 0; i <= info.zhangs[index]; i++){
            ways += process(info, rest - i * info.coins[index], index + 1);
        }
        return ways;
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = new Info(arr);
        int N = info.counts;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - info.coins[index] >= 0){
                    dp[index][rest] += dp[index][rest - info.coins[index]];
                }
                //注意这里的判别，我们需要找的是在给定张数以前的rest的值，所以在张数那里要+1
                if (rest - info.coins[index] * (info.zhangs[index] + 1) >= 0){
                    dp[index][rest] -= dp[index + 1][rest - info.coins[index] * (info.zhangs[index] + 1)];
                }

            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = new Info(arr);
        int N = info.counts;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int i = 0; i <= info.zhangs[index] && i * info.coins[index] <= rest; i++) {
                    ways += dp[index + 1][rest - i * info.coins[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    //对数器
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
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2|| ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
