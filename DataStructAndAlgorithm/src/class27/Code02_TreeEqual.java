package class27;

import java.util.ArrayList;
import java.util.Objects;

// 测试链接 : https://leetcode.cn/problems/subtree-of-another-tree/
public class Code02_TreeEqual {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null){
            return false;
        }
        ArrayList<String> s1 = SerialTree(root);
        ArrayList<String> s2 = SerialTree(subRoot);
        String[] str1 = new String[s1.size()];
        for (int i = 0; i < str1.length; i++) {
            str1[i] = s1.get(i);
        }
        String[] str2 = new String[s2.size()];
        for (int i = 0; i < str2.length; i++) {
            str2[i] = s2.get(i);
        }
        return getIndexOf(str1, str2) != -1;
    }
    //二叉树序列化
    public static ArrayList<String> SerialTree(TreeNode root) {
        ArrayList<String> s = new ArrayList<>();
        process(root, s);
        return s;
    }

    public static void process(TreeNode node, ArrayList<String> s){
        if (node == null){
            s.add(null);
        } else {
            s.add(String.valueOf(node.val));
            process(node.left, s);
            process(node.right, s);
        }
    }

    public static int getIndexOf(String[] str1, String[] str2){
        if (str1 == null || str2 == null || str1.length == 0 || str2.length == 0){
            return -1;
        }
        int x = 0;
        int y = 0;
        int[] next = getNextArray(str2);
        while (x < str1.length && y < str2.length){
            if (isEqual(str1[x], str2[y])){
                x++;
                y++;
            }else if (next[y] == -1){
                x++;
            }else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }
    private static int[] getNextArray(String[] str2) {
        if (str2.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0; //与遍历到的目标字符前一个位置的字符比的位置
        while (i < next.length){
            if (isEqual(str2[i - 1], str2[cn])){
                next[i++] = ++cn;
            } else if (cn > 0){
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }



    public static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        TreeNode subRoot = new TreeNode(1);
        subRoot.left = new TreeNode(2);

        System.out.println(isSubtree(root, subRoot));
    }
}
