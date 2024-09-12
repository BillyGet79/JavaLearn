package class06;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 题目4：
 * 数组中所有数都异或起来的结果，叫做异或和
 * 给定一个数组arr，可以任意切分成若干个不相交的子数组
 * 其中一定存在一种最优方案，使得切出异或和为0的子数组最多
 * 返回这个最多数量
 */
public class Code04_MostXorZero {

    /**
     * 暴力方法
     * 时间复杂度O(n^2)
     * @param arr   要求解的数组
     * @return  返回的最多数量
     */
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] eor = new int[N];
        eor[0] = arr[0];
        for (int i = 1; i < N; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        return process(eor, 1, new ArrayList<>());
    }

    /**
     * 递归函数
     * @param eor   前缀数组
     * @param index index去决定前一部分结不结束。如果结束，就把index放入到parts里去，表示结束了，反之就不放入
     * @param parts 切分点的数组
     * @return  返回题目要求的最多数量
     */
    public static int process(int[] eor, int index, ArrayList<Integer> parts) {
        int ans = 0;
        if (index == eor.length) {
            //把越界位置添加进去（注意parts分割数组保存的都是开区间）
            parts.add(eor.length);
            ans = eorZeroParts(eor, parts);
            //恢复现场行为
            //这里必须恢复现场，不然递归的上游一定会被污染
            parts.remove(parts.size() - 1);
        } else {
            //第一种情况，不拆分，将下一个考虑进去
            int p1 = process(eor, index + 1, parts);
            //第二种情况，拆分
            parts.add(index);
            int p2 = process(eor, index + 1, parts);
            //拆分后要进行现场恢复
            parts.remove(parts.size() - 1);
            ans = Math.max(p1, p2);
        }
        return ans;
    }

    public static int eorZeroParts(int[] eor, ArrayList<Integer> parts) {
        int L = 0;
        int ans = 0;
        for (Integer part : parts) {
            if ((eor[part - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {
                ans++;
            }
            L = part;
        }
        return ans;
    }

    /**
     * 最优解，动态规划方法
     * dp[i]被定义为数组从0到i上能且分出多少个异或和为0的部分（不一定要以i为结尾）
     * 这时候针对于dp[i]就有两种情况
     * 1）dp[i-1]划分的结尾异或和部分不为0，此时直接让dp[i]=dp[i-1]
     * 2）dp[i-1]划分的结尾异或和部分为0，此时我们找上一部分dp[j]，这个j的eor[j]与eor[i]相等（这种情况下我们才能确定以i为结尾的最优划分的最后一块是哪一部分），在dp[j]的基础上+1即可
     * 时间复杂度O(n)
     * @param arr   要求解的数组
     * @return  返回的最多数量
     */
    public static int mostXor(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int xor = 0;
        for (int i = 0; i < N; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }
            map.put(xor, i);
        }
        return dp[N - 1];
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    @Test
    public void testMostXorZero() {
        int testTime = 150000;
        int maxSize = 12;
        int maxValue = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostXor(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
