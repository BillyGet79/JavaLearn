package class20;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code03_Coffee {

    public static class Machine{
        public int timePoint;
        public int workTime;

        public Machine(int timePoint, int workTime){
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }
    public static int minTime1(int[] arr, int n, int a, int b){
        if (arr == null || arr.length == 0){
            return 0;
        }
        PriorityQueue<Machine> heap = new PriorityQueue<>((Comparator.comparingInt(o -> (o.timePoint + o.workTime))));
        for (int j : arr) {
            heap.add(new Machine(0, j));
        }
        //用drinks表示所有人的最快完成时间
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }
        return process(drinks, a, b, 0, 0);
    }


    public static int minTime2(int[] arr, int n, int a, int b){
        if (arr == null || arr.length == 0){
            return 0;
        }
        PriorityQueue<Machine> heap = new PriorityQueue<>((Comparator.comparingInt(o -> (o.timePoint + o.workTime))));
        for (int j : arr) {
            heap.add(new Machine(0, j));
        }
        //用drinks表示所有人的最快完成时间
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }
        return dp(drinks, a, b);
    }

    public static int dp(int[] drinks, int a, int b) {
        int maxFree = 0;
        for (int i = 0; i < drinks.length; i++) {
            maxFree = Math.max(drinks[i], maxFree) + a;
        }
        int N = drinks.length;
        int[][] dp = new int[N + 1][maxFree + 1];
        for (int index = N - 1; index >= 0; index--){
            for (int free = 0; free <= maxFree; free++){
                int wash = Math.max(free, drinks[index]) + a;
                //如果当前遍历到的洗刷结束时间大于业务最大值，直接跳过，这个位置肯定不用填
                if (wash > maxFree){
                    continue;
                }
                int next1 = dp[index + 1][wash];
                int p1 = Math.max(wash, next1);
                int dry = drinks[index] + b;
                int next2 = dp[index + 1][free];
                int p2 = Math.max(dry, next2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    //drinks[0...index - 1]都已经干净了
    //drinks[index...]都想变干净
    //washLine表示洗的机器何时可用
    public static int process(int[] drinks, int a, int b, int index, int washLine){
        if (index == drinks.length - 1){
            return Math.min(Math.max(washLine, drinks[index]) + a, drinks[index] + b);
        }
        //每一个杯子都有两种选择：洗或者挥发
        //所以我们要用递归的方式来做选择
        //第一种情况：选择洗杯子
        int wash = Math.max(washLine, drinks[index]) + a;
        int next1 = process(drinks, a, b, index + 1, wash);
        //由于木桶原理，所以这里一定是选最大的
        int p1 = Math.max(wash, next1);
        //第二种情况，选择挥发
        int dry = drinks[index] + b;
        int next2 = process(drinks, a, b, index + 1, washLine);
        int p2 = Math.max(dry, next2);
        //选择这两种情况的最小
        return Math.min(p1, p2);
    }


    //对数器
    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
//            int ans1 = right(arr, n, a, b);
            int ans2 = minTime1(arr, n, a, b);
            int ans3 = minTime2(arr, n, a, b);
            if (ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }

}
