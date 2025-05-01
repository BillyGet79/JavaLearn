package Linklist_Stack_Queue;

/**
 * Linklist
 *
 * @author 29096
 * @version 1.0
 * @date 2025/5/1
 * @description TODO
 */
public class Linklist {
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
}
