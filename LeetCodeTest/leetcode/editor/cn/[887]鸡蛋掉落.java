//给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。 
//
// 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。 
//
// 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎
//，则可以在之后的操作中 重复使用 这枚鸡蛋。 
//
// 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？ 
//
// 示例 1： 
//
// 
//输入：k = 1, n = 2
//输出：2
//解释：
//鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。 
//否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。 
//如果它没碎，那么肯定能得出 f = 2 。 
//因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。 
// 
//
// 示例 2： 
//
// 
//输入：k = 2, n = 6
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：k = 3, n = 14
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= 100 
// 1 <= n <= 10⁴ 
// 
//
// Related Topics 数学 二分查找 动态规划 👍 969 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    //这个算法未优化，超时
    public int superEggDrop2(int k, int n) {
        if (n < 1 || k < 1) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        int[][] dp = new int[n + 1][k + 1];
        //初始化
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i != dp.length; i++) {
            for (int j = 2; j != dp[0].length; j++) {
                int min = Integer.MAX_VALUE;
                //依次尝试得到结果
                for (int rightEnd = 1; rightEnd != i + 1; rightEnd++) {
                    min = Math.min(min, Math.max(dp[rightEnd - 1][j - 1], dp[i - rightEnd][j]));
                }
                //最后的结果一定要+1
                dp[i][j] = min + 1;
            }
        }
        return dp[n][k];
    }

    //四边形不等式优化
    public int superEggDrop3(int k, int n) {
        if (n < 1 || k < 1) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        int[][] dp = new int[n + 1][k + 1];
        int[][] best = new int[n + 1][k + 1];
        //初始化
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i != dp[0].length; i++) {
            dp[1][i] = 1;
            best[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = k; j > 1; j--) {
                int ans = Integer.MAX_VALUE;
                int bestChoose = 1;
                //对于down和up，这里要好好分析
                int down = best[i - 1][j];
                //如果当前所在的位置是数组的右边界，那么这个元素是没有右边的依赖的
                //这个时候定义遍历上限就要把down到i都要遍历一遍
                int up = j == k ? i : best[i][j + 1];
                for (int rightEnd = down; rightEnd <= up; rightEnd++) {
                    int cur = Math.max(dp[rightEnd - 1][j - 1], dp[i - rightEnd][j]);
                    if (cur <= ans) {
                        ans = cur;
                        bestChoose = rightEnd;
                    }
                }
                dp[i][j] = ans + 1;
                best[i][j] = bestChoose;
            }
        }
        return dp[n][k];
    }

    //最优解
    //这一段代码要仔细思考
    public int superEggDrop(int k, int n) {
        if (n < 1 || k < 1) {
            return 0;
        }
        int[] dp = new int[k];
        int res = 0;
        while (true) {
            res++;
            //通过这样的方式，顺便把初始化都解决了
            //previous记录的是dp[i-1][j-1]的值
            int previous = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];//这里的tmp记录的是dp[i][j-1]的值
                dp[i] = dp[i] + previous + 1;
                previous = tmp;
                if (dp[i] >= n) {
                    return res;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
