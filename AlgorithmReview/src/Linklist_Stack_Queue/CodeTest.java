package Linklist_Stack_Queue;

import org.junit.Before;
import org.junit.Test;

/**
 * CodeTest
 *
 * @author 29096
 * @version 1.0
 * @date 2025/5/1
 * @description TODO
 */
public class CodeTest {
    private Linklist linklist;

    @Before
    public void setUp() throws Exception {
        linklist = new Linklist();
    }

    /**
     * 通过数组创建一个单链表
     * @param arr
     * @return
     */
    public Node getLinklistFromArray(int[] arr) {
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * 根据数组创建双向链表
     * @param arr
     * @return
     */
    public DoubleNode getDoubleLinklistFromArray(int[] arr) {
        DoubleNode head = new DoubleNode(arr[0]);
        head.last = null;
        DoubleNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new DoubleNode(arr[i]);
            DoubleNode temp = current;
            current = current.next;
            current.last = temp;
        }
        return head;
    }

    /**
     * 打印单链表
     * @param head
     */
    public void printLinklist(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 打印双向链表
     * @param head
     */
    public void printDoubleList(DoubleNode head) {
        DoubleNode current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Test
    public void testReverseLinkedList() {
        int[] test = new int[]{1, 2, 3, 4, 5};
        Node head = getLinklistFromArray(test);
        head = linklist.reverseLinkedList(head);
        printLinklist(head);
    }

    @Test
    public void testReverseDoubleList() {
        int[] test = new int[]{1, 2, 3, 4, 5};
        DoubleNode head = getDoubleLinklistFromArray(test);
        head = linklist.reverseDoubleList(head);
        printDoubleList(head);
    }


}
