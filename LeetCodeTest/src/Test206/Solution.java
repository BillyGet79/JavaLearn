package Test206;

import java.util.Scanner;
import java.util.Stack;

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
     * 先尝试用栈来解决这个问题
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        head = stack.pop();
        cur = head;
        while (!stack.isEmpty()) {
            ListNode tmp = stack.pop();
            cur.next = tmp;
            cur = cur.next;
        }
        cur.next = null;
        return head;
    }

    /**
     * 此处使用双指针来解决
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //定义一个头结点，方便操作
        ListNode prev = new ListNode(Integer.MIN_VALUE);
        prev.next = head;
        ListNode slow = prev, fast = prev.next;
        while (fast != null) {
            ListNode tmp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = tmp;
        }
        prev.next.next = null;
        return slow;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode head = new ListNode(sc.nextInt());
        ListNode cur = head;
        for (int i = 0; i < n - 1; i++) {
            head.next = new ListNode(sc.nextInt());
            head = head.next;
        }
        Solution solution = new Solution();
        ListNode result = solution.reverseList2(cur);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
