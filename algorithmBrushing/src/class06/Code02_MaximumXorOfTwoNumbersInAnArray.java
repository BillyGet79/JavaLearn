package class06;

import org.junit.Test;

/**
 * 本题测试链接 : https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 */
public class Code02_MaximumXorOfTwoNumbersInAnArray {

    public static class Node {
        public Node[] nexts = new Node[2];
    }

    /**
     * 使用了上一道题的前缀树的代码
     */
    public static class NumTrie {
        //头节点
        public Node head = new Node();

        //把某个数字newNum加入到这棵前缀树里
        //num是一个32位的整数，所以加入的过程一共走32步
        public void add(int newNum) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                //从高位到低位，取出每一位的状态
                int path = ((newNum >> move) & 1);
                //无路创建，有路复用
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        public int maxXor(int sum) {
            Node cur = head;
            int ans = 0;
            for (int move = 31; move >= 0; move--) {
                int path = ((sum >> move) & 1);
                //期待的路（这里要注意，int类型是有符号的，当move==31的时候，需要把符号位考虑到，当符号位为0的时候，一定期望遇到0（1同理），此时期望为正数）
                int best = move == 31 ? path : (path ^ 1);
                //实际走的路
                best = cur.nexts[best] != null ? best : (best ^ 1);
                ans |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }
    public static int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        //定义前缀树
        NumTrie trie = new NumTrie();
        trie.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, trie.maxXor(nums[i]));
            trie.add(nums[i]);
        }
        return max;
    }
}
