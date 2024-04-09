package Test1402;

import java.util.Arrays;

public class Solution {

    //先使用动态规划的方法进行求解
    //先写暴力递归
    public static int maxSatisfaction1(int[] satisfaction) {
        if (satisfaction == null || satisfaction.length == 0) {
            return 0;
        }
        //先进行排序
        //这里肯定要降序排序，仔细思考一下
        Arrays.sort(satisfaction);
        return process(satisfaction, 0, 0);
    }

    private static int process(int[] satisfaction, int index, int chosen){
        //base case
        if (index == satisfaction.length){
            return 0;
        }
        //第一种情况，选这道菜去做
        int p1 = satisfaction[index] * (chosen + 1) + process(satisfaction, index + 1, chosen + 1);
        //第二种情况，不选这道菜去做
        int p2 = process(satisfaction, index + 1, chosen);
        //取最大值
        return Math.max(p1, p2);
    }

    //上述方法在LeetCode当中超出时间限制，我们现在实现动态规划算法
    public static int maxSatisfaction2(int[] satisfaction){
        if (satisfaction == null || satisfaction.length == 0) {
            return 0;
        }
        Arrays.sort(satisfaction);
        int N = satisfaction.length;
        int[][] dp = new int[N + 1][N + 1];
        //初始化，因为index=0时全部为0，这里初始化不用做
        //直接开始遍历
        for (int index = N - 1; index >= 0; index--){
            for (int chosen = N - 1; chosen >= 0; chosen--){
                int p1 = satisfaction[index] * (chosen + 1) + dp[index + 1][chosen + 1];
                int p2 = dp[index + 1][chosen];
                dp[index][chosen] = Math.max(p1, p2);
            }
        }
        return dp[0][0];
    }

    //使用贪心算法计算这道题
    //贪心算法我们需要在排序之后从大到小进行贪心计算
    public static int maxSatisfaction3(int[] satisfaction){
        if (satisfaction == null || satisfaction.length == 0) {
            return 0;
        }
        Arrays.sort(satisfaction);
        int ans = 0;
        int sum = 0;
        for (int i = satisfaction.length - 1; i >= 0; i--){
            sum += satisfaction[i];
            if (sum > 0){
                ans += sum;
            } else {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] satisfaction = new int[]{-1,-8,0,5,-7};
        System.out.println(maxSatisfaction3(satisfaction));
    }

}

