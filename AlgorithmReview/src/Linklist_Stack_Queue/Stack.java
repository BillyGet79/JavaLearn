package Linklist_Stack_Queue;

/**
 * Stack
 *
 * @author 29096
 * @version 1.0
 * @date 2025/5/4
 * @description
 */
public class Stack {
    //头指针
    public Node head;

    public boolean isEmpty() {
        return head == null;
    }
    //入栈
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }
    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        int data = head.value;
        head = head.next;
        return data;
    }
    //输出栈顶值
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return head.value;
    }
}
