package class41;

public class Code02_BestSplitForEveryPosition {

    //最暴力的方法，一个一个去试
    public static int[] bestSplit1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        for (int range = 1; range < N; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = 0;
                for (int L = 0; L <= s; L++) {
                    sumL += arr[L];
                }
                int sumR = 0;
                for (int R = s + 1; R <= range; R++) {
                    sumR += arr[R];
                }
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
        }
        return ans;
    }

    //比较暴力的方法，在上一个问题的基础上进行计算
    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }
    public static int[] bestSplit2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        for (int range = 1; range < N; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = sum(sum, 0, s);
                int sumR = sum(sum, s + 1, range);
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
        }
        return ans;
    }

    //最优解
    public static int[] bestSplit3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        //求前缀和数组
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        //记录最优划分
        int best = 0;
        for (int range = 0; range < N; range++) {
            while (best + 1 < range) {
                //向右移动前找最小值
                int before = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
                //向右移动后找最小值
                int after = Math.min(sum(sum, 0, best + 1), sum(sum, best + 2, range));
                if (after >= before) {
                    //如果向右移动后比向右移动前要大，则划分右移
                    best++;
                } else {
                    //划分不需要改变
                    break;
                }
            }
            ans[range] = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
        }
        return ans;
    }


    //对数器
    public static int[] randomArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        return ans;
    }

    public static boolean isSameArray(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        int N = arr1.length;
        for (int i = 0; i < N; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int[] ans1 = bestSplit1(arr);
            int[] ans2 = bestSplit2(arr);
            int[] ans3 = bestSplit3(arr);
            if (!isSameArray(ans1, ans2) || !isSameArray(ans1, ans3)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
