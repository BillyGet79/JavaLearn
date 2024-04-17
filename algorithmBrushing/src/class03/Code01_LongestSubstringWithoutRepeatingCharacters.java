package class03;

/**
 * 题目1
 * 求一个字符串中，最长无重复字符子串长度
 * 本题测试链接 : <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">...</a>
 */
public class Code01_LongestSubstringWithoutRepeatingCharacters {

    //使用严格位置依赖的动态规划
    //由于没有空间压缩，所以是没有办法跑AC100%的
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        //先转换成字符串
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        //base case
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = str[i - 1] == str[i] ? 1 : 2;
        }
        //这个dp数组我们只用上半三角
        //我们最终要求得的是dp[0][N - 1];
        for (int left = N - 3; left >= 0; left--) {
            for (int right = left + 2; right < N; right++) {
                if (dp[left][right - 1] != right - left || dp[left + 1][right] != right - left) {
                    dp[left][right] = Math.max(dp[left][right - 1], dp[left + 1][right]);
                } else {
                    if (str[left] != str[right]) {
                        dp[left][right] = dp[left][right - 1] + 1;
                    } else {
                        dp[left][right] = dp[left][right - 1];
                    }
                }
            }
        }
        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
