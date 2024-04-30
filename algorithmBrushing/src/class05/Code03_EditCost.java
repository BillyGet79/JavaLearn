package class05;

/**
 * 题目3
 * 编辑距离问题
 * 本题对三个操作策略进行了代价赋值，在LeetCode有一个类似的题目在记录操作数，其实是一个道理
 * 链接如下（可以根据下面的代码做改进，或者三个代价全部改为1也可以）：<a href="https://leetcode.cn/problems/edit-distance/description/">...</a>
 */
public class Code03_EditCost {
    /**
     * 动态规划样本对应模型
     * 我们定义dp[i][j]为在str1取前i个字符前缀，变为str2取前j个字符前缀，所需要花费的最小代价
     * 自然，dp[0][0] = 0
     * 当空串编辑为有字符的串的时候，只能采取添加策略，所以dp[0][j]为ic*j
     * 当把有字符的串编辑为空串，只能采取删除策略，所以dp[i][0]为dc*i
     * 对于任意的普通位置dp[i][j]，有以下几种变换策略
     * 1）对于str1变为str2，我们可以不考虑当前i位置的元素，让str1前i-1个前缀变为str2前j个前缀，然后把第i个位置的元素删掉，此时dp[i][j] = dp[i - 1][j] + dc
     * 2）对于str1变为str2，我们可以让前i个前缀变为str2前j-1个前缀，然后把str2的第j个位置的元素加上，此时dp[i][j] = dp[i][j - 1] + ic
     * 3）对于str1变为str2，我们可以让前i-1个前缀编辑成str2前j-1个前缀，然后把str1的第i个位置的元素变为str2的第j个元素。当然在这种情况下我们需要判断两个元素是否相等
     * 如果两个元素相等，那么就不需要添加变更策略代价，反之就要加上
     * 此时dp[i][j] = dp[i - 1][j - 1] + (str[i] == str[j] ? 0 : rc)
     * 当然，第三种可能性我们可以将其拆分为两个情况，即相等和不相等分别考虑
     * @param s1    要变动的字符串
     * @param s2    目标字符串
     * @param ic    添加策略代价
     * @param dc    删除策略代价
     * @param rc    变更策略代价
     * @return  最小的变更代价
     */
    public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length + 1;
        int M = str2.length + 1;
        int[][] dp = new int[N][M];
        //base case
        //初始化操作
        for (int i = 1; i < N; i++) {
            dp[i][0] = dc * i;
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = ic * j;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[N- 1][M - 1];
    }
}
