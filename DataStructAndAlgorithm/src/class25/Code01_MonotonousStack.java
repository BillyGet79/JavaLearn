package class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code01_MonotonousStack {

    public static int[][] getNearLessNoRepeat(int[] arr1) {
        int[][] res = new int[arr1.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr1.length; i++) {
            //先将比自己大的元素弹出结算
            while (!stack.isEmpty() && arr1[stack.peek()] > arr1[i]){
                int index = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[index][0] = leftLessIndex;
                res[index][1] = i;
            }
            //然后把自己压入栈中
            stack.push(i);
        }
        //如果最终不为空，就把所有的弹出结算
        while (!stack.isEmpty()){
            int index = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[index][0] = leftLessIndex;
            res[index][1] = -1;
        }
        return res;
    }

    //这其中用到了addLast和getLast，有点犯规，但是明白意思就好
    public static int[][] getNearLess(int[] arr2) {
        int[][] res = new int[arr2.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr2.length; i++) {
            //先将比自己大的元素弹出结算
            while (!stack.isEmpty() && arr2[stack.peek().getLast()] > arr2[i]){
                List<Integer> list = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().getLast();
                for (Integer integer : list) {
                    res[integer][0] = leftLessIndex;
                    res[integer][1] = i;
                }
            }
            //如果遍历的元素与当前相等
            if (!stack.isEmpty() && arr2[stack.peek().getLast()] == arr2[i]){
                stack.peek().addLast(i);
            }else {
                List<Integer> list = new ArrayList<>();
                list.addLast(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()){
            List<Integer> list = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().getLast();
            for (Integer i : list) {
                res[i][0] = leftLessIndex;
                res[i][1] = -1;
            }
        }
        return res;
    }

    //对数器
    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }




}
