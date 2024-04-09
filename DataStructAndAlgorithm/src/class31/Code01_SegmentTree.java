package class31;

public class Code01_SegmentTree {
    public static class SegmentTree{
        private int MAXN;
        //下标从1开始的数组，将原数组信息存放于此
        private int[] arr;
        //模拟线段树维护区间和
        private int[] sum;
        //累加和懒惰标记
        private int[] lazy;
        //更新的值
        private int[] change;
        //更新懒惰标记，表示change数组上的对应的下标的值是否有效
        private boolean[] update;
        public SegmentTree(int[] origin){
            MAXN = origin.length + 1;
            arr = new int[MAXN];
            //arr[0]不用，从1开始
            for (int i = 1; i < MAXN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[MAXN << 2];   //用来支持脑补概念中，某一个范围的累加和信息
            lazy = new int[MAXN << 2];  //用来支持脑补概念中，某一个范围没有往下传递的累加任务
            change = new int[MAXN << 2];    //用来支持脑补概念中，某一个范围有没有更新操作的任务
            update = new boolean[MAXN << 2];    //用来支持脑补概念中，某一个范围更新任务，更新成了什么
        }
        //求一个sum值，这里用了位运算，这样运算比较快
        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }
        //在初始化阶段，先把sum数组，填好
        //在arr[l ~ r]范围上，去build，1~N
        //rt : 这个范围在sum中的下标
        //这个方法是个递归方法，要好好理解
        public void build(int l, int r, int rt){
            //当l==r时，那说明是原先数组单个元素为区间的节点，这个时候可以将原先数组的值直接赋值上去，不需要进行别的操作
            if (l == r){
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }
        //L ~ R 所有的值变成C
        //l ~ r 当前任务下发到了某个范围
        //rt    到哪里找到l~r的信息
        public void update(int L, int R, int C ,int l, int r, int rt){
            //如果此时的更新任务把当前节点所表示的范围全包了
            if (L <= l && r <= R){
                //更新update数组，表示当前的change有效
                update[rt] = true;
                //更新change数组对应的当前值
                change[rt] = C;
                //更新节点sum信息
                sum[rt] = C * (r - l + 1);
                //当前lazy置为0，因为是更新而不是累加，所以之前不管攒了多少的累加和全部清空
                lazy[rt] = 0;
                return;
            }
            //当前任务包不住节点，则下发
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid){
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid){
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }
        //对某个范围上的所有数字加或减某个值
        //L~R, C    这个表示的是任务
        //rt, l~r   当前下发来到了哪个任务
        public void add(int L, int R, int C, int l, int r, int rt) {
            //任务如果把此时的范围全包了！
            if (L <= l && r <= R){
                //调整累加和
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            //任务没有把当前范围全包
            int mid = (r + l) >> 1;
            //把之前的任务下发给自己的子节点
            pushDown(rt, mid - l + 1, r - mid);
            //接收新任务
            //如果当前节点范围中值比任务左边界大，则向左子树派发任务
            if (L <= mid){
                add(L, R, C, l, mid, rt << 1);
            }
            //如果当前节点范围中值比任务右边界小，则向右子树派发任务
            if (R > mid){
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            //调整自己节点的累加和
            pushUp(rt);
        }
        //rt    当前节点下标
        //ln    左子树元素节点个数
        //rn    右孩子元素节点个数
        private void pushDown(int rt, int ln, int rn){
            //先检查是否有值更新的，再检查是否有累加和的
            //如果当前的update有效
            if (update[rt]){
                //左右两个孩子的update全部改为true
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                //更新左右两个孩子的change中的值
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                //将左右两个孩子的累加和数组全部置为0
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                //将左右两个孩子的节点sum值更新
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                //将当前节点的信息设置为无效
                update[rt] = false;
            }
            //如果当前该节点的懒数组信息不为0，那么我们需要将当前拥有的任务向下调整
            if (lazy[rt] != 0){
                //任务下发到到左子树
                lazy[rt << 1] += lazy[rt];
                //左子树上的sum全部加和
                sum[rt << 1]  += lazy[rt] * ln;
                //任务下发到右子树
                lazy[rt << 1 | 1] += lazy[rt];
                //右子树上的sum全部加和
                sum[rt << 1 | 1] += lazy[rt] * rn;
                //当前懒信息清零
                lazy[rt] = 0;
            }
            //这里要思考一个问题，为什么要先执行更新任务再执行累加任务
            //如果我们更新一个节点的话，其lazy值一定会被置为0
            //如果执行这两个if语句的时候，更新和累加的数组在当前节点下都不为0，那么说明一定是先更新后累加的，所以更新在前，累加在后
        }
        //查询累加和
        public long query(int L, int R, int l, int r, int rt){
            if (L <= l && r <= R){
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid){
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid){
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }


    //纯暴力结构
    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    //对数器
    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = { 2, 1, 1, 2, 3, 4, 5 };
        SegmentTree seg = new SegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }
}
