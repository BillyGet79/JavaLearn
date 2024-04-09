package class23;

public class Code02_SplitSumClosedSizeHalf {

    public static int right(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        for (int i : arr){
            sum += i;
        }
        sum /= 2;
        //说明是偶数
        if ((arr.length & 1) == 0){
            return process(arr, 0, arr.length / 2, sum);
        } else {
            return Math.max(process(arr, 0, arr.length / 2, sum), process(arr, 0, arr.length / 2 + 1, sum));
        }
    }
    public static int process(int[] arr, int index, int pick, int rest){
        if (index == arr.length){
            return pick == 0 ? 0 : -1;
        }
        int p1 = process(arr, index + 1, pick, rest);
        int p2 = -1;
        int next = - 1;
        if (arr[index] <= rest){
            next = process(arr, index + 1, pick - 1, rest - arr[index]);
        }
        if (next != -1){
            p2 = arr[index] + next;
        }
        return Math.max(p1, p2);
    }

    public static int dp(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        for (int i : arr){
            sum += i;
        }
        sum /= 2;
        int N = arr.length;
        int M = (N + 1) / 2;
        int[][][] dp = new int[N + 1][M + 1][sum + 1];
        //初始化
        for (int picks = 1; picks <= M; picks++){
            for (int rest = 0; rest <= sum; rest++){
                dp[N][picks][rest] = -1;
            }
        }
        for (int index = N - 1; index >= 0; index--){
            for (int picks = 0; picks <= M; picks++){
                for (int rest = 0; rest <= sum; rest++){
                    int p1 = dp[index + 1][picks][rest];
                    int p2 = -1;
                    int next = -1;
                    if (arr[index] <= rest && picks - 1 >= 0){
                        next = dp[index + 1][picks - 1][rest - arr[index]];
                    }
                    if (next != -1){
                        p2 = arr[index] + next;
                    }
                    dp[index][picks][rest] = Math.max(p1, p2);
                }
            }
        }
        if ((arr.length & 1) == 0){
            return dp[0][N / 2][sum];
        }else {
            return Math.max(dp[0][N / 2][sum], dp[0][N / 2 + 1][sum]);
        }
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
//            int ans3 = dp2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
//                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
