package class04;

/**
 * 题目5
 * n个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 * 测试链接 : <a href="https://leetcode.cn/problems/candy/">...</a>
 */
public class Code05_CandyProblem {

    /**
     * 这道题我们使用贪心算法来解决
     * 贪心策略是这样的：
     *  先从左往右遍历，数组第1一个元素为1，后面如果比左边元素大，就++，如果小于等于左边元素，就重置为1，这样一趟下来，每个元素左边坡度什么情况就都知道了
     *  再从右往左以相同方式遍历，这样一趟下来，每个元素右边坡度什么请开给你就都知道了
     *  最后两个数组每个对应元素取最大值，然后累加即可
     * @param ratings
     * @return
     */
    public static int candy1(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int N = ratings.length;
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        right[N - 1] = 1;
        for (int i = 1; i < N; i++) {
            left[i] = ratings[i] > ratings[i - 1] ? left[i - 1] + 1 : 1;
            right[N - i - 1] = ratings[N - i - 1] > ratings[N - i] ? right[N - i] + 1 : 1;
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

    /**
     * 最优的解法，但是coding比较难以实现
     * 看得懂就看，看不懂可以不看，解题思路明白即可
     * 主要思路是这样的：
     *  从左向右遍历，找递增和递减区间，然后两个区间向上累加，峰值取左右两边的最大值即可
     * 但是在coding层面上处理会非常复杂
     * @param ratings
     * @return
     */
    public static int candy2(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        //记录处理到哪里了
        int index = nextMinIndex2(ratings, 0);
        //记录最终结果
        int res = rightCandy(0, index++);
        //记录左递增区间的累计糖果数量
        int lbase = 1;
        //记录下一个递增区间在哪里
        int next = 0;
        //记录递减区间所要给的糖果数
        int rcands = 0;
        //记录递减区间的累计糖果数
        int rbase = 0;
        while (index != ratings.length) {
            //这个时候说明在递增区间
            if (ratings[index] > ratings[index - 1]) {
                //结果加上lbase+1
                res += ++lbase;
                index++;
            } else if (ratings[index] < ratings[index - 1]) {   //这个时候来到了递减区间
                //先找到下一个递增区间，即递减区间末尾在哪里
                next = nextMinIndex2(ratings, index - 1);
                //然后记录递减区间所要给的糖果数
                rcands = rightCandy(index - 1, next++);
                //计算有区间累计糖果数
                rbase = next - index + 1;
                //这个时候要注意两个区间的峰值处理，要减去累计最小的那个
                res += rcands + (rbase > lbase ? -lbase : -rbase);
                //重置左区间累计糖果数量
                lbase = 1;
                //将指针指向下一个递增区间
                index = next;
            } else {    //这个时候遇到相等的值了
                //加1，lbase重置为1即可
                res += 1;
                lbase = 1;
                index++;
            }
        }
        return res;
    }
    //找到下一个递增区间最左侧的位置
    public static int nextMinIndex2(int[] arr, int start) {
        for (int i = start; i != arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }
    //计算在这个递增或者递减区间上所要给予的糖果数量
    public static int rightCandy(int left, int right) {
        int n = right - left + 1;
        return n + n * (n - 1) / 2;
    }

    /**
     * 现在在上面的基础上加一个条件，即相邻孩子的分数一样，则糖果数必须一样
     * 这种情况下我们只需要特殊处理相同值即可
     * 只需要在相同的情况下继承前面的值即可
     * @param ratings
     * @return
     */
    public static int candy3(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int N = ratings.length;
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        right[N - 1] = 1;
        for (int i = 1; i < N; i++) {
            left[i] = ratings[i] > ratings[i - 1] ? left[i - 1] + 1 : (ratings[i] == ratings[i - 1] ? left[i - 1] : 1);
            right[N - i - 1] = ratings[N - i - 1] > ratings[N - i] ? right[N - i] + 1 : (ratings[N - i - 1] == ratings[N - i] ? right[N - i] : 1);
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

    /**
     * 进阶问题的最优解
     * 代码看得懂就看，看不懂看个乐子就行
     * （这是能把人写吐的东西🤮）
     * @param arr
     * @return
     */
    public static int candy4(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = nextMinIndex3(arr, 0);
        int[] data = rightCandyAndBase(arr, 0, index++);
        int res = data[0];
        int lbase = 1;
        int same = 1;
        int next = 0;
        while (index != arr.length) {
            if (arr[index] > arr[index - 1]) {
                res += ++lbase;
                same = 1;
                index++;
            } else if (arr[index] < arr[index - 1]) {
                next = nextMinIndex3(arr, index - 1);
                data = rightCandyAndBase(arr, index - 1, next++);
                if (data[1] <= lbase) {
                    res += data[0] - data[1];
                } else {
                    res += -lbase * same + data[0] - data[1] + data[1] * same;
                }
                index = next;
                lbase = 1;
                same = 1;
            } else {
                res += lbase;
                same++;
                index++;
            }
        }
        return res;
    }

    public static int[] rightCandyAndBase(int[] arr, int left, int right) {
        int base = 1;
        int cands = 1;
        for (int i = right - 1; i >= left; i--) {
            if (arr[i] == arr[i + 1]) {
                cands += base;
            } else {
                cands += ++base;
            }
        }
        return new int[]{cands, base};
    }

    public static int nextMinIndex3(int[] arr, int start) {
        for (int i = start; i != arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }
}
