package class30;

public class Code01_MorrisTraversal {

    public static class Node{
        public int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }
    }
    public static void process(Node root){
        if (root == null){
            return;
        }
        //1
        process(root.left);
        //2
        process(root.right);
        //3
    }

    public static void morris(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    //先序遍历Morris实现
    public static void morrisPre(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    //有左树的情况下第一次遇到就打印
                    visit(cur);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                //没有左树遇到立马打印
                visit(cur);
            }
            cur = cur.right;
        }
    }

    //中序遍历Morris实现
    public static void morrisIn(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            //当前节点没有左树的时候，直接就能跳到这里遍历
            //如果当前节点有右树且第二次遇到，则不会执行上面if中的continue语句，就会跳出判断来到这句
            //所以这里只需要加这一行就可以
            visit(cur);
            cur = cur.right;
        }
    }

    //后序遍历Morris实现
    public static void morrisPos(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    //当一个节点到达两次的时候，逆序打印其右边界
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        //当Morris遍历完成时，打印整棵树的右边界
        printEdge(head);
        System.out.println();
    }
    //打印右边界
    public static void printEdge(Node head){
        //先反转过来
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null){
            System.out.println(cur.value + " ");
            cur = cur.right;
        }
        //最后在反转回去
        reverseEdge(tail);
    }
    //右边界反转
    public static Node reverseEdge(Node from){
        Node pre = null;
        Node next = null;
        while (from != null){
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    public static void visit(Node cur){
        System.out.print(cur.value + " ");
    }

    public static boolean isBST(Node head){
        if (head == null){
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        //保存中序遍历的上一个遍历节点是什么
        Integer pre = null;
        boolean ans = true;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre >= cur.value){
                ans = false;
            }
            pre = cur.value;
            cur = cur.right;
        }
        return ans;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        //先序遍历顺序：4 2 1 3 6 5 7
        morrisPre(head);
        System.out.println();
        //中序遍历顺序：1 2 3 4 5 6 7
        morrisIn(head);
        System.out.println();
        //后序遍历顺序：1 3 2 5 6 7 4
        morrisPos(head);
    }

}
