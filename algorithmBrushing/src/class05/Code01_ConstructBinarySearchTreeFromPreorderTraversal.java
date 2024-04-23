package class05;

import class04.Code08_TheSkylineProblem;

import java.util.Arrays;
import java.util.Stack;

/**
 * 题目1
 * 给定一个整数数组，它表示BST(即 二叉搜索树 )的 先序遍历 ，构造树并返回其根。
 * 保证 对于给定的测试用例，总是有可能找到具有给定需求的二叉搜索树。
 * 二叉搜索树 是一棵二叉树，其中每个节点， Node.left 的任何后代的值 严格小于 Node.val , Node.right 的任何后代的值 严格大于 Node.val。
 * 二叉树的 前序遍历 首先显示节点的值，然后遍历Node.left，最后遍历Node.right。
 * 本题测试链接 : <a href="https://leetcode.cn/problems/construct-binary-search-tree-from-preorder-traversal/">...</a>
 */
public class Code01_ConstructBinarySearchTreeFromPreorderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 题太简单了，这里就不过多叙述了
     * 直接看代码
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder1(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        //用一个递归方法进行建树
        TreeNode head = getBST(preorder, 0, preorder.length - 1);
        return head;
    }

    public TreeNode getBST(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }
        //由于是二叉搜索树的先序遍历，所以数组的第一个元素一定是头节点
        //先把头节点建出来
        TreeNode cur = new TreeNode(preorder[left]);
        //然后数组后续比他小的元素都是其左子树
        //找到比他大的第一个元素
        int index = left + 1;
        while (index <= right && preorder[index] < preorder[left]) {
            index++;
        }
        //递归获得左子树
        cur.left = getBST(preorder, left + 1, index - 1);
        //递归获得右子树
        cur.right = getBST(preorder, index, right);
        return cur;
    }

    /**
     * 上面的方法在LeetCode上时间效率能打败100%，但是距离最优解还差的有点远（LeetCode测试样例比较一般）
     * 我们在process方法中找index需要找到离当前left最近的大于left所指向元素的下标
     * 而这个功能我们可以通过单调栈实现
     * 我们可以先根据给的preorder数组通过单调栈进行预处理，得到所有元素右侧最接近自己的最大值的下标
     * 这当我们在进行getBST的时候，我们就不需要遍历去找index了，直接从数组中拿即可
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int N = preorder.length;
        int[] nearBig = new int[N];
        Arrays.fill(nearBig, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && preorder[stack.peek()] < preorder[i]) {
                nearBig[stack.pop()] = i;
            }
            stack.push(i);
        }
        return process2(preorder, 0, N - 1, nearBig);
    }

    public TreeNode process2(int[] preorder, int left, int right, int[] nearBig) {
        if (left > right) {
            return null;
        }
        int index = nearBig[left] == -1 ? right + 1 : nearBig[left];
        TreeNode cur = new TreeNode(preorder[left]);
        cur.left = process2(preorder, left + 1, index - 1, nearBig);
        cur.right = process2(preorder, index, right, nearBig);
        return cur;
    }
}
