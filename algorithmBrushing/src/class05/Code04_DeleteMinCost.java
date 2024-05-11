package class05;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目4
 * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
 * 比如s1="abcde",s2="axbc"
 */
public class Code04_DeleteMinCost {

    public static int minCost1(String s1, String s2) {
        List<String> s2Subs = new ArrayList<>();
        process(s2.toCharArray(), 0, "", s2Subs);
        s2Subs.sort((o1, o2) -> o2.length() - o1.length());
        for (String str : s2Subs) {
            if (s1.contains(str)) {    //indexOf(contains)底层和KMP算法代价几乎一样，也可以用KMP替代
                return s2.length() - str.length();
            }
        }
        return s2.length();
    }

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
     * @param x
     * @param y
     * @return
     */
    public static int onlyDelete(char[] x, char[] y) {
        if (x.length < y.length) {
            return Integer.MAX_VALUE;
        }
        int N = x.length;
        int M = y.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        return 0;
    }


}
