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

    //不过涉及到字串问题，我们要有一个思维定式，即我们需要考虑以每个位置为结尾的子串
    //这道题我们可以考虑以每个字母为结尾的子串最远不重复能推多远
    //然后所有位置找最大即可
    //所以我们要考虑每一个位置能推多远的决定因素是什么
    //这里有两个决定因素：
    //  1)当前字符上次的位置
    //  2)前一个位置往左推的距离
    //这两个位置，谁离当前位置近，以当前位置为结尾的无重复子串长度就是它
    //不过每个位置我们只需要前一个位置的结果，所以我们不需要保存整张表（做空间压缩）

    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] str = s.toCharArray();
        //因为ASCII码的范围是0~255，所以用一个256大小的数组足以表示所有的字符
        //用map[i]表示i字符上次出现的位置
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int N = str.length;
        int ans = 1;
        int pre = 1;    //上一个位置，向左推了多长
        map[str[0]] = 0;
        for (int i = 1; i < N; i++) {
            int p1 = i - map[str[i]];
            int p2 = pre + 1;
            int cur = Math.min(p1, p2);
            ans = Math.max(ans, cur);
            pre = cur;
            map[str[i]] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
