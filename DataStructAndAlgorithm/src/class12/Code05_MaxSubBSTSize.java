package class12;

// 在线测试链接 : https://leetcode.cn/problems/largest-bst-subtree
public class Code05_MaxSubBSTSize {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static int maxSubBSTSize2(Node head){
        if (head == null){
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }

    public static class Info{
        public int maxBSTSubtreeSize;
        public int allSize;
        public int max;
        public int min;
        public Info(int maxBSTSubtreeSize, int allSize, int max, int min){
            this.allSize = allSize;
            this.max = max;
            this.min = min;
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
        }
    }

    public static Info process(Node x){
        if (x == null){
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max = x.value;
        int min = x.value;
        int allSize = 1;
        if (leftInfo != null){
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null){
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            allSize += leftInfo.allSize;
        }
        int p1 = -1;
        int p2 = -1;
        if (leftInfo != null){
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        if (rightInfo != null){
            p2 = rightInfo.maxBSTSubtreeSize;
        }

        int p3 = -1;
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if (leftBST & rightBST){
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.value);
            boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min  > x.value);
            if (leftMaxLessX & rightMinMoreX){
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }

        int maxBSTSubtreeSize = Math.max(Math.max(p1, p2), p3);


        return new Info(maxBSTSubtreeSize, allSize, max, min);

    }

}
