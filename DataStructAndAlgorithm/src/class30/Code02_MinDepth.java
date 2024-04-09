package class30;

// 本题测试链接 : https://leetcode.cn/problems/minimum-depth-of-binary-tree/
public class Code02_MinDepth {

    public class TreeNode {
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

    //二叉树递归套路方法
    public static int minDepth1(TreeNode root){
        if (root == null){
            return 0;
        }
        return process(root);
    }

    public static int process(TreeNode node){
        if (node.left == null && node.right == null){
            return 1;
        }
        int leftH = Integer.MAX_VALUE;
        if (node.left != null){
            leftH = process(node.left);
        }
        int rightH = Integer.MAX_VALUE;
        if (node.right != null){
            rightH = process(node.right);
        }
        return Math.min(leftH, rightH) + 1;
    }

    //根据Morris遍历改写
    public static int minDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                //记录左树右边界长度大小
                int rightBoardSize = 1;
                //找左树最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    rightBoardSize++;
                    mostRight = mostRight.right;
                }
                //第一次到达该节点
                if (mostRight.right == null){
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {    //第二次到达
                    if (mostRight.left == null){
                        minHeight = Math.min(minHeight, curLevel);
                    }
                    curLevel -= rightBoardSize;
                    mostRight.right = null;
                }
            } else {    //只有到达一次
                curLevel++;
            }
            cur = cur.right;
        }
        //找整棵树的右边界，找最右节点看其是否为叶子节点
        int finalRight = 1;
        cur = root;
        while (cur.right != null){
            finalRight++;
            cur = cur.right;
        }
        if (cur.left == null && cur.right == null){
            minHeight = Math.min(minHeight, finalRight);
        }
        return minHeight;
    }
}
