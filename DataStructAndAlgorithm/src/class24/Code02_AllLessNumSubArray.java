package class24;

import java.util.LinkedList;

public class Code02_AllLessNumSubArray {

    public static int right(int[] arr, int sum){
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int num(int[] arr, int sum){
        if (arr == null || arr.length == 0 || sum < 0){
            return 0;
        }
        int ans = 0;
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int N = arr.length;
        int R = 0;
        for (int L = 0; L < N; L++) {
            while (R < N){
                //最大值队列调整
                while (!qmax.isEmpty() && arr[qmax.getLast()] <= arr[R]){
                    qmax.removeLast();
                }
                qmax.addLast(R);
                //最小值队列调整
                while (!qmin.isEmpty() && arr[qmin.getLast()] >= arr[R]){
                    qmin.removeLast();
                }
                qmin.addLast(R);
                //判断弹出或者继续加
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > sum){
                    break;
                }else {
                    R++;
                }
            }
            //while出来之后R就不能继续向右移动了
            ans += R - L;
            if (qmax.getFirst() == L){
                qmax.removeFirst();
            }
            if (qmin.getFirst() == L){
                qmin.removeFirst();
            }
        }
        return ans;
    }

    //对数器
    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
