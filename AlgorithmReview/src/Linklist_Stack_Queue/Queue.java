package Linklist_Stack_Queue;

/**
 * Queue
 *
 * @author 29096
 * @version 1.0
 * @date 2025/5/4
 * @description TODO
 */
public class Queue {
    public Node front;
    public Node rear;
    public int queueSize;
    public boolean isEmpty() {
        return front == null && front == rear && queueSize == 0;
    }
    //入队操作
    public void enqueue(int data) {
        //如果队列为空，则新建一个结点，然后让rear指向与front一样的位置
        if (isEmpty()) {
            front = new Node(data);
            rear = front;
            queueSize++;
        }
        Node node = new Node(data);
        node.next = rear;
        rear = node;
        queueSize++;
    }
    //出队操作
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int data = front.value;
        front = front.next;
        if (queueSize == 1) {
            rear = front;
        }
        queueSize--;
        return data;
    }
}
