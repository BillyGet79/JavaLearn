package Linklist_Stack_Queue;

/**
 * Linklist
 *
 * @author 29096
 * @version 1.0
 * @date 2025/5/1
 * @description TODO
 */
public class NodeCode {
    /**
     * 单链表实现链表反转
     * @param head
     * @return
     */
    public Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 双链表实现链表反转
     * 将每一个处理的结点的next指针全部置为空，留给pre处理
     * @param head
     * @return
     */
    public DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;

    }

    /**
     * 根据给定值删除链表结点
     * @param head
     * @param num
     * @return
     */
    public Node removeValue(Node head, int num) {
        //由于我们设置的是不带头节点的链表，所以需要检查头结点的值是否为我们要删除的值
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        //当前面没有相应的值之后，开始删除操作
        //注意，此时头节点一定已经调整好了
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


}
