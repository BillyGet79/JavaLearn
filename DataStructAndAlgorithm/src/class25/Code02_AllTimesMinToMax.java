package class25;

import java.util.Stack;

public class Code02_AllTimesMinToMax {

    //暴力解法
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int max2(int[] arr) {
        int ans = Integer.MIN_VALUE;
        //先求出前缀和数组
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }
        //定义单调栈，开始操作
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                //每弹出一个就结算一次
                int index = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                if (leftLessIndex == -1){
                    ans = Math.max(ans, sum[i - 1] * arr[index]);
                }else {
                    ans = Math.max(ans, (sum[i - 1] - sum[leftLessIndex]) * arr[index]);
                }
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()){
            int index = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            if (leftLessIndex == -1){
                ans = Math.max(ans, sum[sum.length - 1] * arr[index]);
            }else {
                ans = Math.max(ans, (sum[sum.length - 1] - sum[leftLessIndex]) * arr[index]);
            }
        }
        return ans;
    }


    public static int[] generateRandomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
