package Test143;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/25
 * @description TODO
 */
public class Solution {
    //用线性表来解决问题
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode node = head;
        //将链表放到线性表上
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        //然后进行重组合
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
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
    ListNode() {}
}
