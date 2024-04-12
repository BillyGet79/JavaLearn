package class01;

import java.util.Arrays;

/**
 * 题目6
 * 给定两个非负数组x和hp，长度都是N，再给定一个正数range
 * x有序，x[i]表示i号怪兽在x轴上的位置；hp[i]表示i号怪兽的血量
 * range表示法师如果站在x位置，用AOE技能打到的范围是：
 * [x - range, x + range]，被打到的每只怪兽损失1点血量
 * 返回要把所有怪兽血量清空，至少需要释放多少次AOE技能
 */
public class Code06_AOE {

    //这道题的贪心策略比较好想。
    //我们先考虑数组最左边的怪兽，因为消灭它必须要击打它当前的血量数，所以我们让AOE边缘范围覆盖到它来计算AOE打击次数
    //然后我们向右移动AOE范围，使得边缘范围上的怪兽血量值为正数，接着这样操作
    //这样我们就能解决这个问题
    //不过对于区间范围内元素的整体加减，我们需要考虑到线段树，使用线段树的方式来优化区间整体数字的加减


    //先只使用贪心策略来解决
    public static int minAoe2(int[] x, int[] hp, int range) {
        int N = x.length;
        int ans = 0;
        //遍历i，获取x[i]和hp[i]
        for (int i = 0; i < N; i++) {
            if (hp[i] > 0) {
                //找到range范围内的右侧怪兽下标
                int triggerPost = i;
                while (triggerPost < N && x[triggerPost] - x[i] <= range) {
                    triggerPost++;
                }
                ans += hp[i];
                //进行aoe掉血操作
                aoe(x, hp, i, triggerPost - 1, range);
            }
        }
        return ans;
    }

    //aoe掉血操作
    public static void aoe(int[] x, int[] hp, int L, int trigger, int range) {
        int N = x.length;
        int RPost = trigger;
        //找到范围内的右边界
        while (RPost < N && x[RPost] - x[trigger] <= range) {
            RPost++;
        }
        //找到要aoe减掉的血量
        int minus = hp[L];
        for (int i = L; i < RPost; i++) {
            hp[i] = Math.max(0, hp[i] - minus);
        }
    }

    //引入线段树来进行解决问题，这样算法能够优化成O(N*logN)
    public static int minAoe3(int[] x, int[] hp, int range) {
        int N = x.length;
        //coverLeft[i]：如果以i为中心点放技能，左侧能影响到哪，下标从1开始，不从0开始
        //coverRight[i]：如果以i为中心点放技能，右侧能影响到哪，下标从1开始，不从0开始
        int[] coverLeft = new int[N + 1];
        int[] coverRight = new int[N + 1];
        int left = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            while (x[i] - x[left] > range) {
                left++;
            }
            while (right < N && x[right] - x[i] <= range) {
                right++;
            }
            coverLeft[i + 1] = left + 1;
            coverRight[i + 1] = right;
        }
        //best[i]：如果i是最左边缘点，选哪个点做技能中心点最好，下标从1开始，不从0开始
        int[] best = new int[N + 1];
        int trigger = 0;
        for (int i = 0; i < N; i++) {
            while (trigger < N && x[trigger] - x[i] <= range) {
                trigger++;
            }
            best[i + 1] = trigger;
        }
        SegmentTree st = new SegmentTree(hp);
        st.build(1, N, 1);
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            long leftEdge = st.query(i, i, 1, N, 1);
            if (leftEdge > 0) {
                ans += leftEdge;
                int t = best[i];
                int l = coverLeft[t];
                int r = coverRight[t];
                st.add(l, r, (int)(-leftEdge), 1, N, 1);
            }
        }
        return ans;
    }

    public static class SegmentTree {
        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;

        public SegmentTree(int[] origin) {
            MAXN = origin.length + 1;
            arr = new int[MAXN];
            for (int i = 1; i < MAXN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[MAXN << 2];
            lazy = new int[MAXN << 2];
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        private void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }

    // 为了测试
    public static int[] randomArray(int n, int valueMax) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * valueMax) + 1;
        }
        return ans;
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int N = arr.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 50;
        int X = 500;
        int H = 60;
        int R = 10;
        int testTime = 50000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] x2 = randomArray(len, X);
            Arrays.sort(x2);
            int[] hp2 = randomArray(len, H);
            int[] x3 = copyArray(x2);
            int[] hp3 = copyArray(hp2);
            int range = (int) (Math.random() * R) + 1;
            int ans2 = minAoe2(x2, hp2, range);
            int ans3 = minAoe3(x3, hp3, range);
            if (ans2 != ans3) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");

        N = 500000;
        long start;
        long end;
        int[] x2 = randomArray(N, N);
        Arrays.sort(x2);
        int[] hp2 = new int[N];
        for (int i = 0; i < N; i++) {
            hp2[i] = i * 5 + 10;
        }
        int[] x3 = copyArray(x2);
        int[] hp3 = copyArray(hp2);
        int range = 10000;

        start = System.currentTimeMillis();
        System.out.println(minAoe2(x2, hp2, range));
        end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + " 毫秒");

        start = System.currentTimeMillis();
        System.out.println(minAoe3(x3, hp3, range));
        end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + " 毫秒");
    }




}
