package class38;

public class Code04_MoneyProblem {

    //d为怪兽的武力
    //p为怪兽要求的钱
    //先使用暴力解的方式来解决
    public static long process(int[] d, int[] p, int ability, int index) {
        //如果来到了最终位置，那么就说明通过了
        if (index == d.length) {
            return 0;
        }
        if (ability < d[index]) {
            //如果当前的能力比当前怪物的武力值小，那么就必须花钱
            return p[index] + process(d, p, ability + d[index], index + 1);
        } else {
            //否则就可以有两种选择，取最小即可
            return Math.min(p[index] + process(d, p, ability + d[index], index + 1), process(d, p, ability, index + 1));
        }
    }
    public static long func1(int[] d, int[] p) {
        return process(d, p, 0, 0);
    }

    public static long func2(int[] d, int[] p) {
        int sum = 0;
        for (int num : d) {
            sum += num;
        }
        long[][] dp = new long[d.length + 1][sum + 1];
        for (int index = d.length - 1; index >= 0; index++) {
            for (int ability = 0; ability <= sum; ability++) {
                //如果ability增加的值超过了边界，那么这次计算就不再考虑，让他为0即可
                if (ability + d[index] > sum) {
                    continue;
                }
                if (ability < d[index]) {
                    dp[index][ability] = p[index] + dp[ability + d[index]][index + 1];
                } else {
                    dp[index][ability] = Math.min(p[index] + dp[ability + d[index]][index + 1], dp[ability][index + 1]);
                }
            }
        }
        return dp[0][0];
    }

    //第二种思路解题
    //首先是暴力递归解法
    public static long process2(int[] d, int[] p, int index, int money) {
        //base case
        if (index == -1) {  //一个怪兽也没遇到
            //如果当前的money为0，那么这个通关方案成立，否则，这个通关方案不成立，返回-1
            return money == 0 ? 0 : -1;
        }
        //index >= 0
        //1、不购买当前index号怪兽
        long preMaxAbility = process2(d, p, index - 1, money);
        long p1 = -1;
        if (preMaxAbility != -1 && preMaxAbility >= d[index]) {
            p1 = preMaxAbility;
        }
        //2、购买当前index号怪兽
        long preMaxAbility2 = process2(d, p, index - 1, money - p[index]);
        long p2 = -1;
        if (preMaxAbility2 != -1) {
            p2 = d[index] + preMaxAbility2;
        }
        return Math.max(p1, p2);
    }

    public static long func3(int[] d, int[] p) {
        int allMoney = 0;
        for (int i = 0; i < p.length; i++) {
            allMoney += p[i];
        }
        int N = d.length;
        for (int money = 0; money < allMoney; money++) {
            if (process2(d, p, N - 1, money) != -1) {
                return money;
            }
        }
        return allMoney;
    }

    public static long func4(int[] d, int[] p) {
        int sum = 0;
        for (int num : p) {
            sum += num;
        }
        int[][] dp = new int[d.length][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][p[0]] = d[0];
        for (int index = 1; index < d.length; index++) {
            for (int money = 0; money <= sum; money++) {
                if (money >= p[index] && dp[index - 1][money - p[index]] != -1) {
                    dp[index][money] = dp[index - 1][money - p[index]] + d[index];
                }
                if (dp[index - 1][money] >= d[index]) {
                    dp[index][money] = Math.max(dp[index][money], dp[index - 1][money]);
                }

            }
        }
        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            if (dp[d.length - 1][i] != -1) {
                ans = i;
                break;
            }
        }
        return ans;
    }
}
