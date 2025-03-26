package Test708;


import java.util.Scanner;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/25
 * @description TODO
 */
public class Solution {
    /**
     * 总的来说分为两种情况，一种是比当前指向的head大的，一种是比当前指向的head小的
     * 如果插入的值比当前指向的head大，那么就顺序向后找到插入的值即可
     * 如果比Head小，那么就需要找到循环点，往循环点后添加
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = head;
            return node;
        }
        if (head.next == head) {
            head.next = node;
            node.next = head;
            return head;
        }
        Node cur = head, next = head.next;
        while (next != head) {
            if (insertVal >= cur.val && insertVal <= next.val) {
                break;
            }
            if (cur.val > next.val) {
                if (insertVal > cur.val || insertVal < next.val) {
                    break;
                }
            }
            cur = cur.next;
            next = next.next;
        }
        cur.next = node;
        node.next = next;
        return head;
    }

    public static void printNode(Node head) {
        if (head.next == head) {
            System.out.println(head.val);
            return;
        }
        Node cur = head;
        System.out.print(cur.val + " ");
        cur = cur.next;
        while (cur != head) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Solution solution = new Solution();
        Node head = null;
        while (in.hasNext()) {
            int insertVal = in.nextInt();
            head = solution.insert(head, insertVal);
            printNode(head);
        }
        in.close();
    }
}

class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
}
