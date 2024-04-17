package class03;

import java.util.*;

/**
 * 题目8
 * 给定三个参数：
 * 二叉树的头节点head，树上某个节点target，正数K
 * 从target开始，可以向上走或者向下走
 * 返回与target的距离是K的所有节点
 */
public class Code08_DistanceKNodes {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 根据二叉树的结构特性，如果我们往上走，就只能找到其父节点，但是如果往下走，既可以找左孩子，也可以找右孩子
     * 我们可以创建一个hashmap，来保存所有节点的父节点，这样我们就能够往上走了
     * 然后我们模拟往上或往下走这一流程即可
     * 自己写的方法，使用的是深度优先遍历的思想
     * @param root
     * @param target
     * @param K
     * @return
     */
    public static List<Node> distanceKNodes1(Node root, Node target, int K) {
        //先找到所有节点的父节点
        HashMap<Node, Node> nodesFather = new HashMap<>();
        getNodesFather(root, nodesFather);
        //这个时候target一定在表里面了
        //然后我们进行尝试走K
        List<Node> ans = new ArrayList<>();
        process1(target, target, ans, nodesFather, K);
        return ans;
    }

    public static void getNodesFather(Node node, HashMap<Node, Node> nodesFather) {
        if (node == null) {
            return;
        }
        //将自己的左孩子添加到表里面
        if (node.left != null) {
            nodesFather.put(node.left, node);
            //这里递归左孩子
            getNodesFather(node.left, nodesFather);
        }
        //再将自己的右孩子添加到表里面
        if (node.right != null) {
            nodesFather.put(node.right, node);
            //这里递归右孩子
            getNodesFather(node.right, nodesFather);
        }
    }

    /**
     * @param cur   当前遍历到的节点
     * @param pre   当前遍历到的节点的上一个节点
     * @param ans   答案数组
     * @param rest  剩余的步数
     */
    public static void process1(Node cur, Node pre, List<Node> ans, HashMap<Node, Node> nodesFather, int rest) {
        //弹出
        if (rest == 0) {
            ans.add(cur);
            return;
        }
        //尝试往上走
        //避免反复横跳装车，所以要判断走过来的节点是否是自己父亲
        if (nodesFather.containsKey(cur) && pre.left != cur && pre.right != cur) {
            process1(nodesFather.get(cur), cur, ans, nodesFather, rest - 1);
        }
        //尝试往下走
        //因为往下走时有可能遇到自己走过来的节点，所以我们用pre记录之前走过来的节点，这样以防反复撞车
        //先尝试往左孩子走
        if (cur.left != null && cur.left != pre) {
            process1(cur.left, cur, ans, nodesFather, rest - 1);
        }
        //在尝试往右走
        if (cur.right != null && cur.right != pre) {
            process1(cur.right, cur, ans, nodesFather, rest - 1);
        }
    }

    /**
     *
     * @param root
     * @param target
     * @param K
     * @return
     */
    public static List<Node> distanceKNodes2(Node root, Node target, int K) {
        //先找到所有节点的父节点
        HashMap<Node, Node> nodesFather = new HashMap<>();
        nodesFather.put(root, null);
        getNodesFather(root, nodesFather);
        //用广搜进行操作
        //广搜队列
        Queue<Node> queue = new LinkedList<>();
        //保存遍历到的节点
        HashSet<Node> visited = new HashSet<>();
        //target节点入队列
        queue.offer(target);
        visited.add(target);
        int curLevel = 0;
        List<Node> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                if (curLevel == K) {
                    ans.add(cur);
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    visited.add(cur.left);
                    queue.offer(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    visited.add(cur.right);
                    queue.offer(cur.right);
                }
                if (nodesFather.get(cur) != null && !visited.contains(nodesFather.get(cur))) {
                    visited.add(nodesFather.get(cur));
                    queue.offer(nodesFather.get(cur));
                }
            }
            curLevel++;
            if (curLevel > K) {
                break;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans1 = distanceKNodes1(root, target, K);
        for (Node o1 : ans1) {
            System.out.println(o1.value);
        }
        List<Node> ans2 = distanceKNodes2(root, target, K);
        for (Node o1 : ans2) {
            System.out.println(o1.value);
        }
    }
}
