package class36;

import java.util.ArrayList;
import java.util.Map;

public class Code02_SkipListMap {

    //跳表的节点定义
    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V val;
        public ArrayList<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K key, V val) {
            this.key = key;
            this.val = val;
            nextNodes = new ArrayList<>();
        }

        //遍历的时候，如果是从右往左遍历到的null(next == null)，遍历结束
        //头(null)，头结点的null，认为最小
        //node里面的key是否比otherKey小，true，不是false
        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null) || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }

    }

    public static class SkipListMap<K extends Comparable<K>, V> {
        private static final double PROBABILITY = 0.5;
        //头节点
        private SkipListNode<K, V> head;
        //跳表大小
        private int size;
        //当前跳表的最大序列值
        private int maxLevel;

        public SkipListMap() {
            head = new SkipListNode<>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        //从最高层开始，一路找下去
        //最终，找到第0层的key的最右的节点
        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            //从最高层开始找
            int level = maxLevel;
            //从头开始找
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                //在这一层里小于key的最右节点在哪里
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            //这里跳出来以后，一定在最后一层
            return cur;
        }

        private SkipListNode<K,V> mostRightLessNodeInLevel(K key, SkipListNode<K,V> cur, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            //从这里跳出来一定是这一层的比目标节点小的最右节点了
            return cur;
        }
        //是否包含key
        public boolean containsKey(K key){
            if (key == null) {
                return false;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }
        //新增、改value
        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            //要先找这个表有没有这个节点
            //先找到小于这个值的最右节点
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            //然后找上面找到节点的下一个节点
            SkipListNode<K, V> find = less.nextNodes.get(0);
            //如果这个节点存在并且这个节点的key与目标的key相同，那么这个操作就只是更新value的行为
            if (find != null && find.isKeyEqual(key)) {
                find.val = value;
            } else {
                //进入到这个判断，就说明所要操作的元素是新增记录
                size++;
                int newNodeLevel = 0;
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                //从这里跳出来序列值就已经确定
                //如果确定的序列值大于目前的序列值，那么就进行扩充
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                //初始化节点
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                for (int i = 0; i <= newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                //从最大序列值开始操作
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    //level层中，找到最右的小于key的节点
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    //当前操作序列值层数小于等于新随机到的序列值的情况下才开始操作
                    if (level <= newNodeLevel) {
                        //上面找到最右节点之后，新节点在该序列值指向的节点为pre的下一个
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        //然后重新设置pre在该序列值的指向，指向新节点
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }
        //得到key的value
        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.val : null;
        }
        //删除操作
        public void remove(K key) {
            //判断是否有这个元素
            if (containsKey(key)) {
                size--;
                //从最高层出发开始操作
                int level = maxLevel;
                //从头节点出发
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    //在该序列值下找到比删除目标值小的最右节点
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    SkipListNode<K, V> next = pre.nextNodes.get(level);
                    //1.在这一层中，pre下一个就是key   执行删除操作
                    //2.在这一层中，pre的下一个key是大于要删除的key  不执行操作
                    //将pre在该序列下的指针指向next在该序列值下指向的节点
                    if (next != null && next.isKeyEqual(key)) {
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    //在level层只有一个节点了，就是默认节点head
                    //这个时候要减少最大序列值
                    if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }
        public K firstKey() {
            //第0层的第一个
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }
        //找最后一个节点，那就直接跳到最后一个节点
        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                SkipListNode<K, V> next = cur.nextNodes.get(level);
                while (next != null) {
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.key : less.key;
        }

        public int size() {
            return size;
        }
    }
    //对数器
    // for test
    public static void printAll(SkipListMap<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.val + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipListMap<String, String> test = new SkipListMap<>();
        printAll(test);
        System.out.println("======================");
        test.put("A", "10");
        printAll(test);
        System.out.println("======================");
        test.remove("A");
        printAll(test);
        System.out.println("======================");
        test.put("E", "E");
        test.put("B", "B");
        test.put("A", "A");
        test.put("F", "F");
        test.put("C", "C");
        test.put("D", "D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.containsKey("B"));
        System.out.println(test.containsKey("Z"));
        System.out.println(test.firstKey());
        System.out.println(test.lastKey());
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
        System.out.println("======================");
        test.remove("D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
    }
}
