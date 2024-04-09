package class47;

public class Code02_RestoreWays {

    //纯暴力解法
    public static int ways0(int[] arr) {
        return process0(arr, 0);
    }

    public static int process0(int[] arr, int index) {
        if (index == arr.length) {
            return isValid(arr) ? 1 : 0;
        } else {
            if (arr[index] != 0) {
                return process0(arr, index + 1);
            } else {
                int ways = 0;
                for (int v = 1; v < 201; v++) {
                    arr[index] = v;
                    ways += process0(arr, index + 1);
                }
                arr[index] = 0;
                return ways;
            }
        }
    }

    public static boolean isValid(int[] arr) {
        if (arr[0] > arr[1]) {
            return false;
        }
        if (arr[arr.length - 1] > arr[arr.length - 2]) {
            return false;
        }
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > Math.max(arr[i - 1], arr[i + 1])) {
                return false;
            }
        }
        return true;
    }

    //初版暴力递归解法
    public static int ways1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        //如果不为0，那么就说明这个数是确定的，直接调用即可
        if (arr[N - 1] != 0) {
            return process1(arr, N - 1, arr[N - 1], 2);
        } else {
            //如果为0，那么这个数就是我们要遍历寻找的，所以定义一个变量来记录我们可以取到的值
            int ways = 0;
            for (int v = 1; v < 201; v++) {
                ways += process1(arr, N - 1, v, 2);
            }
            return ways;
        }
    }

    //整体上是一个深度优先搜索方法
    public static int process1(int[] arr, int i, int v, int s) {
        if (i == 0) {
            //此时就剩最后一个位置了，对于0位置的数，左边的数是默认比他小的，右边的数必须大于等于它才行
            //如果说当前的数为0或者当前的数是没丢的，则可以直接返回1
            //否则返回0，说明这次深度搜索是不成立的
            return ((s == 0 || s == 1) && (arr[i] == 0 || v == arr[i])) ? 1 : 0;
        }
        if (arr[i] != 0 && v != arr[i]) {
            //如果当前的值没丢但是其值并不为当前i位置上的值
            //这说明这个调用是不成立的，返回0即可
            return 0;
        }
        //遍历到了下面，说明i位置的数是可以变成v的，接下来根据v和s来确定我们调用的下一个v是什么
        //先定义一个变量来记录答案
        int ways = 0;
        //右边的数大于等于v的情况
        //这个时候下一个数可以随意取，但是要注意与v之间的关系
        if (s == 0 || s == 1) {
            for (int pre = 1; pre < 201; pre++) {
                ways += process1(arr, i - 1, pre, pre < v ? 0 : (pre == v ? 1 : 2));
            }
        } else {
            //右边的数小于v的情况
            //这个时候进行下一步递归的时候，值必须大于等于v，否则当前是没法取v的
            for (int pre = v; pre < 201; pre++) {
                ways += process1(arr, i - 1, pre, pre == v ? 1 : 2);
            }
        }
        return ways;
    }

    //动态规划版本
    //这个算法包含了优化策略
    public static int ways2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][][] dp = new int[N][201][3];
        //base case
        if (arr[0] != 0) {
            dp[0][arr[0]][0] = 1;
            dp[0][arr[0]][1] = 1;
        } else {
            for (int v = 1; v < 201; v++) {
                dp[0][v][0] = 1;
                dp[0][v][1] = 1;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int v = 1; v < 201; v++) {
                for (int s = 0; s < 3; s++) {
                    //只有值可以修改或者当前v与原数组值相等的时候，才可以进行操作
                    if (arr[i] == 0 || v == arr[i]) {
                        //注意中间这一步的改进，非常关键
                        if (s == 0 || s == 1) {
                            for (int pre = 1; pre < v; pre++) {
                                dp[i][v][s] += dp[i - 1][pre][0];
                            }
                        }
                        dp[i][v][s] += dp[i - 1][v][1];
                        for (int pre = v + 1; pre < 201; pre++) {
                            dp[i][v][s] += dp[i - 1][pre][2];
                        }
                    }
                }
            }
        }
        if (arr[N - 1] != 0) {
            return dp[N - 1][arr[N - 1]][2];
        } else {
            int ways = 0;
            for (int v = 1; v < 201; v++) {
                ways += dp[N - 1][v][2];
            }
            return ways;
        }
    }

    //优化过后的动态规划版本
    public static int sum(int begin, int end, int relation ,int[][] presum) {
        return presum[end][relation] - presum[begin - 1][relation];
    }

    public static int ways3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][][] dp = new int[N][201][3];
        //base case
        if (arr[0] != 0) {
            dp[0][arr[0]][0] = 1;
            dp[0][arr[0]][1] = 1;
        } else {
            for (int v = 1; v < 201; v++) {
                dp[0][v][0] = 1;
                dp[0][v][1] = 1;
            }
        }
        //初始化presum数组，上面计算了base case，所以我们用i=0的位置进行初始化
        int[][] presum = new int[201][3];
        for (int v = 1; v < 201; v++) {
            for (int s = 0; s < 3; s++) {
                presum[v][s] = presum[v - 1][s] + dp[0][v][s];
            }
        }
        for (int i = 1; i < N; i++) {
            for (int v = 1; v < 201; v++) {
                for (int s = 0; s < 3; s++) {
                    if (arr[i] == 0 || arr[i] == v) {
                        if (s == 0 || s == 1) {
                            dp[i][v][s] += sum(1, v - 1, 0, presum);
                        }
                        dp[i][v][s] += dp[i - 1][v][1];
                        dp[i][v][s] += sum(v + 1, 200, 2, presum);
                    }
                }
            }
            //更新presum数组
            for (int v = 1; v < 201; v++) {
                for (int s = 0; s < 3; s++) {
                    presum[v][s] = presum[v - 1][s] + dp[i][v][s];
                }
            }
        }
        if (arr[N - 1] != 0) {
            return dp[N - 1][arr[N - 1]][2];
        } else {
            //因为要返回的是一个累加和，且位置为i位置的所有累加和，所以直接用现有的presum来计算即可
            return sum(1, 200, 2, presum);
        }
    }

    // for test
    public static int[] generateRandomArray(int len) {
        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            if (Math.random() < 0.5) {
                ans[i] = 0;
            } else {
                ans[i] = (int) (Math.random() * 200) + 1;
            }
        }
        return ans;
    }

    //对数器
    // for test
    public static void printArray(int[] arr) {
        System.out.println("arr size : " + arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 4;
        int testTime = 15;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * len) + 2;
            int[] arr = generateRandomArray(N);
            int ans0 = ways0(arr);
            int ans1 = ways1(arr);
            int ans2 = ways2(arr);
            int ans3 = ways3(arr);
            if (ans0 != ans1 || ans2 != ans3 || ans0 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("功能测试结束");
        System.out.println("===========");
        int N = 100000;
        int[] arr = generateRandomArray(N);
        long begin = System.currentTimeMillis();
        ways3(arr);
        long end = System.currentTimeMillis();
        System.out.println("run time : " + (end - begin) + " ms");
    }


}
