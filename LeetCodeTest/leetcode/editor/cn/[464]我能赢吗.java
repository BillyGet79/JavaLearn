//在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和 达到或超过 100 的玩家，即为胜者。 
//
// 如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？ 
//
// 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。 
//
// 给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），若先出手的玩家能稳赢则返回 
//true ，否则返回 false 。假设两位玩家游戏时都表现 最佳 。 
//
// 
//
// 示例 1： 
//
// 
//输入：maxChoosableInteger = 10, desiredTotal = 11
//输出：false
//解释：
//无论第一个玩家选择哪个整数，他都会失败。
//第一个玩家可以选择从 1 到 10 的整数。
//如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
//第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
//同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
// 
//
// 示例 2: 
//
// 
//输入：maxChoosableInteger = 10, desiredTotal = 0
//输出：true
// 
//
// 示例 3: 
//
// 
//输入：maxChoosableInteger = 10, desiredTotal = 1
//输出：true
// 
//
// 
//
// 提示: 
//
// 
// 1 <= maxChoosableInteger <= 20 
// 0 <= desiredTotal <= 300 
// 
//
// Related Topics 位运算 记忆化搜索 数学 动态规划 状态压缩 博弈 👍 531 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    //暴力递归解法
    public boolean canIWin0(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        //由于不能选择重复数字，所以必须要将所有的情况记录在表中
        int[] arr = new int[maxChoosableInteger];
        for (int i = 0; i < maxChoosableInteger; i++) {
            arr[i] = i + 1;
        }
        return process(arr, desiredTotal);
    }

    public static boolean process(int[] arr, int rest) {
        if (rest <= 0) {
            return false;
        }
        //先手尝试所有的情况
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                //先手的决定
                int cur = arr[i];
                arr[i] = -1;
                boolean next = process(arr, rest - cur);
                //注意这里要恢复现场
                arr[i] = cur;
                //下面这句的含义是，如果子过程的先手赢不了的话，那么当前赢
                if (!next) {
                    return true;
                }
            }
        }
        return false;
    }

    //进行状态的压缩
    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if ((maxChoosableInteger * (maxChoosableInteger + 1) >> 1) < desiredTotal) {
            return false;
        }
        return process1(maxChoosableInteger, 0, desiredTotal);
    }

    //status表示状态
    public static boolean process1(int choose, int status ,int rest) {
        if (rest <= 0) {
            return false;
        }
        for (int i = 1; i <= choose; i++) {
            //如果当前位的状态为0，那么就代表这一位没有使用过
            if (((1 << i) & status) == 0) {
                //传入的时候将当前位变为1
                if (!process1(choose, status | (1 << i), rest - i)) {
                    return true;
                }
            }
        }
        return false;
    }

    //转变为动态规划算法
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if ((maxChoosableInteger * (maxChoosableInteger + 1) >> 1) < desiredTotal) {
            return false;
        }
        int[] dp = new int[1 << (maxChoosableInteger + 1)];
        return process2(maxChoosableInteger, 0, desiredTotal, dp);
    }

    //重点理解这一段代码
    //因为无论status通过什么顺序得到的当前的状态，其最终对应的rest一定是一样的，所以rest不需要进行缓存
    //换句话来讲，status的一个状态一定对应着一个固定的rest，所以rest不需要去管
    public static boolean process2(int choose, int status, int rest, int[] dp) {
        if (dp[status] != 0) {
            return dp[status] == 1 ? true : false;
        }
        boolean ans = false;
        if (rest > 0) {
            for (int i = 1; i <= choose; i++) {
                if (((1 << i) & status) == 0) {
                    if (!process2(choose, (status | (1 << i)), rest - i, dp)) {
                        ans = true;
                        break;
                    }
                }
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
