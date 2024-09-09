package class05;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目4
 * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
 * 比如s1="abcde",s2="axbc"
 */
public class Code04_DeleteMinCost {

    /**
     * 最暴力的解法
     * @param s1    第一个字符串
     * @param s2    第二个字符串
     * @return  删除的最少字符数量
     */
    public static int minCost1(String s1, String s2) {
        List<String> s2Subs = new ArrayList<>();
        //找到s2的所有字串
        process(s2.toCharArray(), 0, "", s2Subs);
        s2Subs.sort((o1, o2) -> o2.length() - o1.length());
        //排序后看所有的字串是否为s1的字串，如果有一个是，那么直接返回长度差值
        for (String str : s2Subs) {
            if (s1.contains(str)) {    //indexOf(contains)底层和KMP算法代价几乎一样，也可以用KMP替代
                return s2.length() - str.length();
            }
        }
        return s2.length();
    }

    //递归找所有字串
    public static void process(char[] str2, int index, String path, List<String> list) {
        if (index == str2.length) {
            list.add(path);
            return;
        }
        process(str2, index + 1, path, list);
        process(str2, index + 1, path + str2[index], list);
    }

    /**
     * x字符串只通过删除的方式，变到y字符串
     * 返回至少要删除几个字符
     * 如果变不成，返回Integer.MAX_VALUE
     * @param x 要进行删除的字符串
     * @param y 目标字符串
     * @return  返回至少要删除几个字符
     */
    public static int onlyDelete(char[] x, char[] y) {
        if (x.length < y.length) {
            return Integer.MAX_VALUE;
        }
        int N = x.length;
        int M = y.length;
        int[][] dp = new int[N + 1][M + 1];
        // dp[i][j]表示前缀长度
        // dp数组初始化
        // 先把右上半区全部初始化为最大值
        for (int i = 0; i <= N ; i++) {
            for (int j = 0; j <= M ; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        // 考虑dp数组的第一列，x变为长度为0的空字符串，一定是要删除全部的元素，所以第0列直接填行下标即可
        for (int i = 1; i <= N; i++ ) {
            dp[i][0] = i;
        }
        dp[0][0] = 0;
        // 短字符串无法通过删除的方式变为长字符串，所以dp表的右上半区全部都为最大值
        // 所以在进行遍历的时候我们要避免遍历到右上半区
        // 注意，这张表极大概率不是正方形的（当然有可能是正方形），一定是行多列少的长方形，所以注意yi的边界判断
        for (int xi = 1; xi <= N; xi++) {
            // 所以在列的调整上要取M和xi的最小值
            for (int yi = 1; yi <= Math.min(M, xi); yi++) {
                // 讨论普遍位置
                dp[xi][yi] = Integer.MAX_VALUE;
                // 如果前一个位置是一个有效的解，那么我们可以让当前位置的元素删除，这样剩下的位置就匹配上了
                if (dp[xi - 1][yi] != Integer.MAX_VALUE) {
                    dp[xi][yi] = dp[xi - 1][yi] + 1;
                }
                // 如果当前两个遍历到的位置的元素相同，那么如果前面字串
                if (x[xi - 1] == y[yi - 1] && dp[xi - 1][yi - 1] != Integer.MAX_VALUE) {
                    dp[xi][yi] = Math.min(dp[xi][yi], dp[xi - 1][yi - 1]);
                }
            }
        }
        return dp[N][M];
    }

    @Test
    public void testOnlyDelete() {
        char[] x = {'a', 'b', 'c', 'd'};
        char[] y = {'a', 'x'};
        System.out.println(onlyDelete(x, y));
    }

    /**
     * 解法二
     * 生成所有s1的子串
     * 然后考察每个子串和s2的编辑距离（假设编辑距离只有删除动作且删除一个字符的代价为1）
     * @param s1
     * @param s2
     * @return
     */
    public static int minCost2(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty()) {
            return s2.length();
        }
        int ans = Integer.MAX_VALUE;
        char[] str2 = s2.toCharArray();
        for (int start = 0; start < s1.length(); start++) {
            for (int end = start + 1; end <= s1.length(); end++) {
                ans = Math.min(ans, onlyDelete(str2, s1.substring(start, end).toCharArray()));
            }
        }
        return ans == Integer.MAX_VALUE ? s2.length() : ans;
    }

    //测试代码
    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    @Test
    public void testMinCost() {
        int str1Len = 20;
        int str2Len = 10;
        int v = 5;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans1 = minCost1(str1, str2);
            int ans2 = minCost2(str1, str2);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
