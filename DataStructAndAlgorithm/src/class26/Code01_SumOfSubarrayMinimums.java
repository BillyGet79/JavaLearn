package class26;

import java.util.Stack;

// 测试链接：https://leetcode.cn/problems/sum-of-subarray-minimums/

public class Code01_SumOfSubarrayMinimums {

    //暴力解法
    public static int subArrayMinSum1(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = arr[i];
                for (int k = i + 1; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                ans += min;
            }
        }
        return ans;
    }

    // 没有用单调栈
    public static int subArrayMinSum2(int[] arr) {
        // left[i] = x : arr[i]左边，离arr[i]最近，<=arr[i]，位置在x
        int[] left = leftNearLessEqual2(arr);
        // right[i] = y : arr[i]右边，离arr[i]最近，< arr[i],的数，位置在y
        int[] right = rightNearLess2(arr);
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int start = i - left[i];
            int end = right[i] - i;
            ans += start * end * arr[i];
        }
        return ans;
    }

    public static int[] leftNearLessEqual2(int[] arr) {
        int N = arr.length;
        int[] left = new int[N];
        for (int i = 0; i < N; i++) {
            int ans = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {
                    ans = j;
                    break;
                }
            }
            left[i] = ans;
        }
        return left;
    }

    public static int[] rightNearLess2(int[] arr) {
        int N = arr.length;
        int[] right = new int[N];
        for (int i = 0; i < N; i++) {
            int ans = N;
            for (int j = i + 1; j < N; j++) {
                if (arr[i] > arr[j]) {
                    ans = j;
                    break;
                }
            }
            right[i] = ans;
        }
        return right;
    }

    //单调栈优化算法
    public static int sumSubarrayMins(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                int index = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                int times = (index - leftLessIndex) * (i - index);
                ans += arr[index] * times;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int index = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            int times = (index - leftLessIndex) * (arr.length - index);
            ans += arr[index] * times;
        }
        return ans;
    }

    //对数器
    public static int[] randomArray(int len, int maxValue) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue) + 1;
        }
        return ans;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = subArrayMinSum1(arr);
            int ans2 = subArrayMinSum2(arr);
            int ans3 = sumSubarrayMins(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
