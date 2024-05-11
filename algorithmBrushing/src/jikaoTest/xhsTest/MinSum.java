package jikaoTest.xhsTest;

import java.awt.print.PrinterGraphics;
import java.io.*;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 求点赞数最少
 */
public class MinSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        int length = (int) in.nval;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            in.nextToken();
            arr[i] = (int) in.nval;
        }
        for (int i = 0; i < length; i++) {
            out.println(getMinSum(arr, i));
        }
        out.flush();
    }

    /**
     * 以index为基准的情况下，得到的最小总和是多少
     * @param arr
     * @param index
     * @return
     */
    public static int getMinSum(int[] arr, int index) {
        int length = arr.length;
        if (length <= 1) {
            return 0;
        }
        if (length == 2) {
            return index == 0 ? (arr[0] < arr[1] ? -1 : arr[0] + arr[1]) : (arr[0] > arr[1] ? -1 : arr[0] + arr[1]);
        }
        //为了不改变原先的数组内容，所以这里我们要搞一个备份
        int[] num = new int[length];
        for (int i = 0; i < length; i++) {
            num[i] = arr[i];
        }
        //定义优先级队列
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        //定义一个变量记录当前小根堆的最大值是多少
        int max = num[index];
        //定义一个变量记录sum
        int sum = 0;
        //将除了index的所有数据全部放入到优先级队列当中
        for (int i = 0; i < length; i++) {
            sum += num[i];
            if (i != index) {
                queue.add(num[i]);
                max = Math.max(num[i], max);
            }
        }
        //然后进行逐个+1操作
        while (true) {
            if (num[index] >= max) {
                break;
            }
            num[index]++;
            sum++;
            if (num[index] >= max) {
                break;
            }
            int cur = queue.poll();
            cur++;
            sum++;
            max = Math.max(cur, max);
            queue.add(cur);
        }
        return sum;
    }
}
