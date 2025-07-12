package Linklist_Stack_Queue;

/**
 * QueueByArray
 *
 * @author 29096
 * @version 1.0
 * @date 2025/5/4
 * @description TODO
 */
public class QueueByArray {
    private int[] arr;
    private int front;
    private int rear;
    private int size;
    private final int limit;

    public QueueByArray(int limit) {
        arr = new int[limit];
        front = 0;
        rear = 0;
        size = 0;
        this.limit = limit;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断下一个下标是哪一个
     * @param i
     * @return
     */
    private int nextIndex(int i) {
        return i < limit - 1 ? i + 1 : 0;
    }

    


}
