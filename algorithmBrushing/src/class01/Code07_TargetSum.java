package class01;

import java.util.HashMap;

/**
 * 题目7
 * 给定一个数组arr，你可以在每个数字之前决定+或者-
 * 但是必须所有数字都参与
 * 在给定一个数target，请问最后算出target的方法数是多少
 * LeetCode 494题
 */
public class Code07_TargetSum {

    //方法一，暴力递归，时间复杂度过高，但是AC能过（很神奇吧hhhh）
    public static int findTargetSumWays1(int[] arr, int target) {
        return process1(arr, target, 0, 0);
    }

    public static int process1(int[] arr, int target, int index, int sum) {
        //base case
        //如果最后的计算总和sum与target相等，那么返回1即可
        if (index == arr.length) {
            return sum == target ? 1 : 0;
        }
        //定义两个变量进行累计在该方向下的方法数是多少
        //代码可以写成一行但没必要
        int add = process1(arr, target, index + 1, sum + arr[index]);
        int sub = process1(arr, target, index + 1, sum - arr[index]);
        return add + sub;
    }

    //记忆化搜索（傻缓存方法）
    public static int findTargetSumWays2(int[] arr, int target) {
        return process2(arr, target, 0, 0, new HashMap<>());
    }

    public static int process2(int[] arr, int target, int index, int sum ,HashMap<Integer, HashMap<Integer, Integer>> dp) {
        //命中了就不用算了
        if (dp.containsKey(index) && dp.get(index).containsKey(sum)) {
            return dp.get(index).get(sum);
        }
        //命中了那就算
        int ans = 0;
        if (index == arr.length) {
            ans = sum == target ? 1 : 0;
        } else {
            int add = process2(arr, target, index + 1, sum + arr[index], dp);
            int sub = process2(arr, target, index + 1, sum - arr[index], dp);
            ans = add + sub;
        }
        if (!dp.containsKey(index)) {
            dp.put(index, new HashMap<>());
        }
        dp.get(index).put(sum, ans);
        return ans;
    }

    //优化点一：
    //如果我们将这个数组中的元素全部变为非负数，那么是不影响最后的方法数的
    //下面的优化点都会基于这个操作
    //优化点二：
    //如果我们把所有的元素置为非负之后，再求累加和sum，那么target一定小于等于sum
    //优化点三：
    //如果sum与target奇偶性不一样，那么一定0种方法（这个要仔细思考）
    //优化点四：
    //在每一个组成target的方法中，我们把正的数设定为集合P，负的数设定为集合N，那么显然P-N=target
    //这个时候我们将等式两边同时加上P和N，那么得到2P=target+P+N
    //而P+N=sum，所以得到2P=target+sum，故P=(target+sum)/2
    //所以我们只要找到一个集合的总和为P，那么它一定是其中一种方法
    //所以我们整个问题就转化成了找P，即这个非负数组中的哪些数累加和为P
    //于是这道题目就变成了一个纯背包问题

    //由于背包问题的代码已经很少去写了，所以这里我们先从递归入手来写
    public static int findTargetSumWays3(int[] arr, int target) {
        //先把数组中的元素全部置为正数，并且同步计算sum
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                arr[i] = -arr[i];
            }
            sum += arr[i];
        }
        //判断target与sum是否同奇偶
        if (target % 2 != sum % 2) {
            return 0;
        }
        //接着我们进行递归操作
        return process3(arr, 0, (sum + target) / 2);
    }

    public static int process3(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        return process3(arr, index + 1, rest - arr[index]) + process3(arr, index + 1, rest);
    }

    //然后在上面的基础上，我们直接改成动态规划的形式
    public static int findTargetSumWays4(int[] arr, int target) {
        //先把数组中的元素全部置为正数，并且同步计算sum
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                arr[i] = -arr[i];
            }
            sum += arr[i];
        }
        //判断target与sum是否同奇偶
        if (target % 2 != sum % 2 || (target + sum) / 2 < 0) {
            return 0;
        }
        //然后我们进行dp操作
        int L = arr.length;
        int P = (target + sum) / 2;
        int[][] dp = new int[L + 1][P + 1];
        //根据递归base case将dp数组初始化
        dp[L][0] = 1;
        //进行数组遍历，从后往前遍历即可
        for (int index = L - 1; index >= 0; index--) {
            for (int rest = 0; rest <= P; rest++) {
                if (rest - arr[index] >= 0) {
                    dp[index][rest] = dp[index + 1][rest] + dp[index + 1][rest - arr[index]];
                } else {
                    dp[index][rest] = dp[index + 1][rest];
                }
            }
        }
        return dp[0][P];
    }

    //当然还有优化点五，就是动态规划的空间压缩技巧
    //我们可以观察上面的填表过程，每一行index用完之后之后就不会再用了，所以我们可以在此基础上做空间压缩
    public static int findTargetSumWays5(int[] arr, int target) {
        //先把数组中的元素全部置为正数，并且同步计算sum
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                arr[i] = -arr[i];
            }
            sum += arr[i];
        }
        //判断target与sum是否同奇偶
        if (target % 2 != sum % 2 || (target + sum) / 2 < 0) {
            return 0;
        }
        //然后我们进行dp操作
        int P = (target + sum) / 2;
        //在这里我们定义dp数组我们直接定义一维即可
        int[] dp = new int[P + 1];
        dp[0] = 1;
        for (int a : arr) {
            for (int rest = P; rest >= a; rest--) {
                dp[rest] += dp[rest - a];
            }
        }
        return dp[P];
    }
}
