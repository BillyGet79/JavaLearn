package class24;

import java.util.LinkedList;
import java.util.Queue;

public class Code01_SlidingWindowMaxArray {

    public static class Node{
        public int value;
        public int index;
        public Node(int index, int value){
            this.index = index;
            this.value = value;
        }
    }

    //自己实现的算法
    public static int[] right(int[] arr, int w){
        if (arr == null || w < 1 || arr.length < w){
            return null;
        }
        int N = arr.length;
        int[] ans = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = L + w - 1;
        LinkedList<Node> queue = new LinkedList<>();
        //先把初始窗口内的元素加进去
        for (int i = 0; i <= R; i++) {
            if (!queue.isEmpty()){
                while (!queue.isEmpty() && queue.getLast().value <= arr[i]){
                    queue.removeLast();
                }
                queue.addLast(new Node(i, arr[i]));
            }else {
                queue.addLast(new Node(i, arr[i]));
            }
        }
        //做完上面操作，先填入当前最大值，然后把窗口向右移动一个
        ans[index++] = queue.getFirst().value;
        R++;
        L++;
        //然后进行向右遍历的操作
        while (R < N){
            //R向右移动后的操作
            while (!queue.isEmpty() && queue.getLast().value <= arr[R]) {
                queue.removeLast();
            }
            queue.addLast(new Node(R, arr[R]));
            //L向右移动后的操作
            while (queue.getFirst().index < L){
                queue.removeFirst();
            }
            //向ans数组添加数据
            ans[index++] = queue.getFirst().value;
            R++;
            L++;
        }
        return ans;
    }

    //优化过后的算法
    // 其实根本没必要用一个类去存储index和value，因为数组本身是不会变的，只需要存入index即可
    // 当我们需要比对的时候，直接通过存入的index找到相应的值即可
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // qmax 窗口最大值的更新结构
        // 放下标
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int R = 0; R < arr.length; R++) {
            while (!qmax.isEmpty() && arr[qmax.getLast()] <= arr[R]){
                qmax.removeLast();
            }
            qmax.addLast(R);
            if (qmax.getFirst() == R - w){
                qmax.removeFirst();
            }
            if (R >= w - 1){
                res[index++] = arr[qmax.getFirst()];
            }
        }
        return res;
    }

    //对数器
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
