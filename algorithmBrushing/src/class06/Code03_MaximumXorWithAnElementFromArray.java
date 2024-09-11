package class06;

/**
 * 测试链接 : https://leetcode.cn/problems/maximum-xor-with-an-element-from-array/
 * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。
 * 如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
 */
public class Code03_MaximumXorWithAnElementFromArray {

    /**
     * 对原先的节点定义进行修改
     * 我们对每个结点添加一个min属性，来保存当前节点下面的所有结点的最小值是多少
     */
    public static class Node {
        public int min;
        public Node[] nexts;

        public Node() {
            min = Integer.MAX_VALUE;
            nexts = new Node[2];
        }
    }

    public static class NumTrie {
        public Node head = new Node();

        /**
         * 添加操作
         * 在添加的时候要将当前的最小值进行修改
         * @param num   要添加到前缀树的数字
         */
        public void add(int num) {
            Node cur = head;
            head.min = Math.min(num, head.min);
            for (int move = 30; move >= 0; move--) {
                int path = ((num >> move) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
                cur.min = Math.min(cur.min, num);
            }
        }

        /**
         * 在进行获得异或和最大值的时候，向下判断要进行值判断
         * @param x 进行异或的数字
         * @param m 异或不要超过的元素大小
         * @return  返回不超过m的异或元素所获得的最大异或和
         */
        public int maxXorWithXBehindM(int x, int m) {
            //如果最小值大于m了，那么就没有符合标准的元素了，直接返回-1
            if (head.min > m) {
                return -1;
            }
            Node cur = head;
            int ans = 0;
            for (int move = 30; move >= 0; move--) {
                int path = (x >> move) & 1;
                //期待遇到的
                int best = path ^ 1;
                best ^= (cur.nexts[best] == null || cur.nexts[best].min > m) ? 1 : 0;
                //此时best就变为了实际遇到的
                //这个时候直接异或到ans当中即可
                ans |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }

    public static int[] maximizeXor(int[] nums, int[][] queries) {
        int N = nums.length;
        NumTrie trie = new NumTrie();
        for (int i = 0; i < N; i++) {
            trie.add(nums[i]);
        }
        int M = queries.length;
        int[] ans = new int[M];
        for (int i = 0; i < M; i++) {
            ans[i] = trie.maxXorWithXBehindM(queries[i][0], queries[i][1]);
        }
        return ans;
    }
}
