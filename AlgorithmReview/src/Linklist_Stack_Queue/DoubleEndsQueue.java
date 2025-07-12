package Linklist_Stack_Queue;

/**
 * DoubleEndsQueue
 *
 * @author 29096
 * @version 1.0
 * @date 2025/5/4
 * @description TODO
 */
public class DoubleEndsQueue {
    public DoubleNode head;
    public DoubleNode tail;
    //从头部添加元素
    public void addFromHead(int value) {
        DoubleNode cur = new DoubleNode(value);
        if (head == null) {
            head = cur;
            tail = cur;
        } else {
            cur.next = head;
            head.last = cur;
            head = cur;
        }
    }
    //从尾部添加
    public void addFromTail(int value) {
        DoubleNode cur = new DoubleNode(value);
        if (tail == null) {
            head = cur;
            tail = cur;
        } else {
            cur.last = tail;
            tail.next = cur;
            tail = cur;
        }
    }
    //从头部出队列
    public int popFromHead() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        DoubleNode cur = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            cur.next = null;
            head.last = null;
        }
        return cur.value;
    }
    //从尾部出队列
    public int popFromTail() {
        DoubleNode cur = tail;
        if (tail == head) {
            tail = null;
            head = null;
        } else {
            tail = tail.last;
            cur.last = null;
            tail.next = null;
        }
        return cur.value;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
