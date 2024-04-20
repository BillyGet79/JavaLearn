package class04;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 题目1
 * 今日头条原题
 *
 * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)。意思是在数组里下标0~3这个范围上，有几个2？返回2。
 * 假设给你一个数组arr，对这个数组的查询非常频繁，请返回所有查询的结果
 */
public class Code01_QueryHobby {

    public static class QueryBox1 {
        private int[] arr;

        public QueryBox1(int[] array) {
            arr = new int[array.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = array[i];
            }
        }

        public int query(int L, int R, int v) {
            int ans = 0;
            for (; L <= R; L++) {
                if (arr[L] == v) {
                    ans++;
                }
            }
            return ans;
        }
    }

    public static class QueryBox2 {
        private HashMap<Integer, ArrayList<Integer>> map;

        public QueryBox2(int[] arr) {
            map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (!map.containsKey(arr[i])) {
                    map.put(arr[i], new ArrayList<>());
                }
                map.get(arr[i]).addLast(i);
            }
        }

        public int Query(int left, int right, int value) {
            if (!map.containsKey(value)) {
                return 0;
            }
            ArrayList<Integer> indexList = map.get(value);
            int L = findLessRight(indexList, left);
            int R = findLessRight(indexList, right + 1);
            return R - L;
        }
        //<value最右的位置
        private int findLessRight(ArrayList<Integer> indexList, int value) {
            int l = 0;
            int r = indexList.size() - 1;
            int index = -1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (indexList.get(mid) < value) {
                    index = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return index + 1;
        }
    }

    //对数器
    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 30;
        int value = 20;
        int testTimes = 1000;
        int queryTimes = 1000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(len, value);
            int N = arr.length;
            QueryBox1 box1 = new QueryBox1(arr);
            QueryBox2 box2 = new QueryBox2(arr);
            for (int j = 0; j < queryTimes; j++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                int L = Math.min(a, b);
                int R = Math.max(a, b);
                int v = (int) (Math.random() * value) + 1;
                if (box1.query(L, R, v) != box2.Query(L, R, v)) {
                    System.out.println("Oops!");
                    System.out.print("[");
                    for (int k = 0; k < N - 1; k++) {
                        System.out.print(arr[k] + ", ");
                    }
                    System.out.println(arr[N - 1] + "]");
                    System.out.println("L：" + L + " R：" + R + " v：" + v);
                }
            }
        }
        System.out.println("test end");
    }

}
