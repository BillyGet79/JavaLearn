package Test25;

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
     * 使用栈来解决问题
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //将两个链表的数放到栈中
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                stack1.push(p1.val);
                p1 = p1.next;
            }
            if (p2 != null) {
                stack2.push(p2.val);
                p2 = p2.next;
            }
        }
        int carry = 0;
        ListNode head = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int sum = stack1.pop() + stack2.pop() + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
        }
        while (!stack1.isEmpty()) {
            int sum = stack1.pop() + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
        }
        while (!stack2.isEmpty()) {
            int sum = stack2.pop() + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
        }
        if (carry > 0) {
            ListNode node = new ListNode(carry);
            node.next = head;
            head = node;
        }
        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(5);
        ListNode list = solution.addTwoNumbers(l1, l2);
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
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
