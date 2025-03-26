package Test146;

import java.util.HashMap;
import java.util.HashSet;

/**
 * LRUCache
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/10
 * @description TODO
 */
public class LRUCache {
    private int capacity;
    private HashMap<Integer, Integer> map;
    private DoubleLinklist head;
    private DoubleLinklist tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new DoubleLinklist();
        tail = new DoubleLinklist();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key);
        //找到当前该节点
        DoubleLinklist temp = findKeyNode(key);
        //将当前结点移动到双端链表头部
        moveToHead(temp);
        return val;
    }

    private void moveToHead(DoubleLinklist temp) {
        //让前后两个结点连接
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        //在头结点处插入
        temp.next = head.next;
        temp.prev = head;
        head.next.prev = temp;
        head.next = temp;
    }

    //当执行这个方法的时候，说明当前key一定在双端队列里面，所以不需要判断不在的情况
    private DoubleLinklist findKeyNode(int key) {
        DoubleLinklist temp = head.next;
        while (temp.key != key) {
            temp = temp.next;
        }
        return temp;
    }

    public void put(int key, int value) {
        //先检查这个键是否存在
        if (map.containsKey(key)) {
            //如果存在，将其覆盖
            map.replace(key, value);
            //然后找到当前结点，将其插入到头部
            DoubleLinklist temp = findKeyNode(key);
            moveToHead(temp);
        } else {
            //这个键不存在，那么要做的就是插入这个键
            //先判断是否超出容量
            if (map.size() == capacity) {
                //已经到达容量上限，此时需要将最久未使用的结点删除
                removeLast();
            }
            //然后插入该结点
            insertKey(key, value);
        }
    }

    private void insertKey(int key, int value) {
        //先在哈希表插入该节点
        map.put(key, value);
        //然后创建该结点，插入到双端链表当中
        DoubleLinklist node = new DoubleLinklist();
        node.key = key;
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeLast() {
        //先获得到这个要删除的key
        int key = tail.prev.key;
        //删除链表结点
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
        //删除哈希表的内容
        map.remove(key);
    }
}

class DoubleLinklist {
    DoubleLinklist next;
    DoubleLinklist prev;
    int key;
}

