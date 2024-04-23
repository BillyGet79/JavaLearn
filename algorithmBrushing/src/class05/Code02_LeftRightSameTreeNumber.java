package class05;


/**
 * 题目6
 * 如果一个节点X，它左树结构和右树结构完全一样
 * 那么我们说以X为头的子树是相等子树
 * 给定一棵二叉树的头节点head
 * 返回head整棵树上有多少棵相等子树
 */
public class Code02_LeftRightSameTreeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 二叉树的递归套路问题
     * 我们递归找每个节点的两个孩子的相等子树数量，那么以父节点为头的相等子树数量就是两个相等子树之和一个可能加的1
     * 这个可能加的1就是自己是否是个相等子树
     * 我们定义一个布尔类型的函数来判断两个数节点是否结构相等
     * 这个暴力方法时间复杂度为O(N*logN)
     * @param head
     * @return
     */
    public static int sameNumber1(Node head) {
        if (head == null) {
            return 0;
        }
        return sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.left) ? 1 : 0);
    }

    public static boolean same(Node h1, Node h2) {
        if (h1 == null && h2 == null) {
            return true;
        }
        if (h1 == null ^ h2 == null) {
            return false;
        }
        //两个都不为空的情况
        return h1.value == h2.value && same(h1.left, h2.left) && same(h1.right, h2.right);
    }

    /**
     * 时间复杂度为O(N)的方法
     * @param head
     * @return
     */
    public static int sameNumber2(Node head) {
        String algorithm = "SHA-256";
        Hash hash = new Hash(algorithm);
        return process(head, hash).ans;
    }

    public static class Info {
        public int ans;
        public String str;

        public Info(int ans, String str) {
            this.ans = ans;
            this.str = str;
        }
    }

    public static Info process(Node head, Hash hash) {
        if (head == null) {
            return new Info(0, hash.hashCode("#,"));
        }
        Info l = process(head.left, hash);
        Info r = process(head.right, hash);
        int ans = (l.str.equals(r.str) ? 1 : 0) + l.ans + r.ans;
        String str = hash.hashCode(String.valueOf(head.value) + "," + l.str + r.str);
        return new Info(ans, str);
    }


    //对数器
    public static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = sameNumber1(head);
            int ans2 = sameNumber2(head);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");

    }


}
