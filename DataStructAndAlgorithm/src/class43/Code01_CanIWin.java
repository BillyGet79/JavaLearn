package class43;

public class Code01_CanIWin {

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
