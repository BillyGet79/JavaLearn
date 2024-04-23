package class04;

/**
 * 题目7
 * 字符串交错组成
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空子字符串：
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 * 本题测试链接 : <a href="https://leetcode.cn/problems/interleaving-string/">...</a>
 */
public class Code07_InterleavingString {

    /**
     * 看到这道题我们会出现第一个想法，即设定三个指针，走指针对比
     * 但是如果我们这么去做了，那就G了
     * 比如1111kf和111tef组成1111kf111tef，你觉得是前后拼接，实际上是交错字符串（没想到吧hhhh）
     * 这道题的正确解法是使用动态规划中的样本对应模型
     * 我们定义dp[i][j]为str1拿前i个字符，str2拿前j个字符，是否能组成str3前i+j个字符
     * 最后dp表的最右下角的位置的结果就是我们要的答案
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        if (str1.length + str2.length != str3.length) {
            return false;
        }
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        //dp数组初始化
        dp[0][0] = true;
        //我们拿str1的第i个字符与str3的第i个字符比，如果相同，就初始化为true，直到不为true时break掉即可
        for (int i = 1; i < str1.length + 1; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        //与上面同理
        for (int j = 1; j < str2.length + 1; j++) {
            if (str2[j - 1] != str3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        //开始dp
        for (int i = 0; i < str1.length + 1; i++) {
            for (int j = 0; j < str2.length + 1; j++) {
                if (str1[i - 1] == str3[i + j - 1] && dp[i - 1][j] || str2[j - 1] == str3[i + j - 1] && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length][str2.length];
    }
}
