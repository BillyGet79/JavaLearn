package class37;

public class Code02_SlidingWindowMedian {

     public static class SBTNode<K extends Comparable<K>> {
         public K key;
         public SBTNode<K> l;
         public SBTNode<K> r;
         //平衡因子
         public int size;
         public SBTNode(K key) {
             this.key = key;
             size = 1;
         }
     }
     public static class SizeBalancedTreeMap<K extends Comparable<K>> {
         private SBTNode<K> root;
         private SBTNode<K> rightRotate(SBTNode<K> cur) {
             SBTNode<K> leftNode = cur.l;
             cur.l = leftNode.r;
             leftNode.r = cur;
             leftNode.size = cur.size;
             cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
             return leftNode;
         }
         private SBTNode<K> leftRotate(SBTNode<K> cur) {
             SBTNode<K> rightNode = cur.r;
             cur.r = rightNode.l;
             rightNode.l = cur;
             rightNode.size = cur.size;
             cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
             return rightNode;
         }
         private SBTNode<K> maintain(SBTNode<K> cur) {
             if (cur == null) {
                 return null;
             }
             long leftSize = cur.l != null ? cur.l.size : 0;
             long leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
             long leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
             long rightSize = cur.r != null ? cur.r.size : 0;
             long rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
             long rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
             if (leftLeftSize > rightSize) {
                 cur = rightRotate(cur);
                 cur.r = maintain(cur.r);
                 cur = maintain(cur);
             } else if (leftRightSize > rightSize) {
                 cur.l = leftRotate(cur.l);
                 cur = rightRotate(cur);
                 cur.l = maintain(cur.l);
                 cur.r = maintain(cur.r);
                 cur = maintain(cur);
             } else if (rightLeftSize > leftSize) {
                 cur.r = rightRotate(cur.r);
                 cur = leftRotate(cur);
                 cur.l = maintain(cur.l);
                 cur.r = maintain(cur.r);
                 cur = maintain(cur);
             } else if (rightRightSize > leftSize) {
                 cur = leftRotate(cur);
                 cur.l = maintain(cur.l);
                 cur = maintain(cur);
             }
             return cur;
         }
         //找到key的前值
         private SBTNode<K> findLastIndex(K key) {
             SBTNode<K> pre = root;
             SBTNode<K> cur = root;
             while (cur != null) {
                 pre = cur;
                 if (key.compareTo(cur.key) == 0) {
                     break;
                 } else if (key.compareTo(cur.key) < 0) {
                     cur = cur.l;
                 } else {
                     cur = cur.r;
                 }
             }
             return pre;
         }
         //添加元素
         private SBTNode<K> add(SBTNode<K> cur, K key) {
             if (cur == null) {
                 return new SBTNode<K>(key);
             } else {
                 cur.size++;
                 if (key.compareTo(cur.key) < 0) {
                     cur.l = add(cur.l, key);
                 } else {
                     cur.r = add(cur.r, key);
                 }
                 return maintain(cur);
             }
         }
         //删除元素（重点）
         private SBTNode<K> delete(SBTNode<K> cur, K key) {
             cur.size--;
             if (key.compareTo(cur.key) > 0) {
                 cur.r = delete(cur.r, key);
             } else if (key.compareTo(cur.key) < 0) {
                 cur.l = delete(cur.l, key);
             } else {
                 if (cur.l == null && cur.r == null) {
                     cur = null;
                 } else if (cur.l == null && cur.r != null) {
                     cur = cur.r;
                 } else if (cur.l != null && cur.r == null) {
                     cur = cur.l;
                 } else {
                     SBTNode<K> pre = null;
                     SBTNode<K> des = cur.r;
                     des.size--;
                     while (des.l != null) {
                         pre = des;
                         des = des.l;
                         des.size--;
                     }
                     if (pre != null) {
                         pre.l = des.r;
                         des.r = cur.r;
                     }
                     des.l = cur.l;
                     des.size = des.l.size + (des.r != null ? des.r.size : 0);
                     cur = des;
                 }
             }
             return cur;
         }
         private SBTNode<K> getIndex(SBTNode<K> cur, int kth) {
             if (kth == (cur.l != null ? cur.l.size : 0) + 1) {
                 return cur;
             } else if (kth <= (cur.l != null ? cur.l.size : 0)) {
                 return getIndex(cur.l, kth);
             } else {
                 return getIndex(cur.r, kth - (cur.l != null ? cur.l.size : 0) - 1);
             }
         }

         public int size() {
             return root == null ? 0 : root.size;
         }

         public boolean containsKey(K key) {
             if (key == null) {
                 throw new RuntimeException("invalid parameter.");
             }
             SBTNode<K> lastNode = findLastIndex(key);
             return lastNode != null && key.compareTo(lastNode.key) == 0;
         }

         public void add(K key) {
             if (key == null) {
                 throw new RuntimeException("invalid parameter.");
             }
             SBTNode<K> lastNode = findLastIndex(key);
             if (lastNode == null || key.compareTo(lastNode.key) != 0) {
                 root = add(root, key);
             }
         }

         public void remove(K key) {
             if (key == null) {
                 throw new RuntimeException("invalid parameter.");
             }
             if (containsKey(key)) {
                 root = delete(root, key);
             }
         }

         public K getIndexKey(int index) {
             if (index < 0 || index >= this.size()) {
                 throw new RuntimeException("invalid parameter.");
             }
             return getIndex(root, index + 1).key;
         }

     }

    public static class Node implements Comparable<Node> {
        public int index;
        public int value;

        public Node(int i, int v) {
            index = i;
            value = v;
        }

        @Override
        public int compareTo(Node o) {
            return value != o.value ? Integer.valueOf(value).compareTo(o.value)
                    : Integer.valueOf(index).compareTo(o.index);
        }
    }
    public static double[] medianSlidingWindow(int[] nums, int k) {
         SizeBalancedTreeMap<Node> map = new SizeBalancedTreeMap<>();
         //先把前K个数放进有序表中
        //注意，这里添加的数为k-1个数，所以下面的循环第一步添加之后就变成k个数了
        for (int i = 0; i < k - 1; i++) {
            map.add(new Node(i, nums[i]));
        }
        double[] ans = new double[nums.length - k + 1];
        int index = 0;
        for (int i = k - 1; i < nums.length; i++) {
            map.add(new Node(i, nums[i]));
            if (map.size() % 2 == 0) {
                //如果是偶数次，则调用两次index方法，然后求中位数
                Node upmid = map.getIndexKey(map.size() / 2 - 1);
                Node downmid = map.getIndexKey(map.size() / 2);
                ans[index++] = ((double) upmid.value + (double) downmid.value) / 2;
            } else {
                //如果是奇数次，则调用一次index方法即可
                Node mid = map.getIndexKey(map.size() / 2);
                ans[index++] = (double) mid.value;
            }
            map.remove(new Node(i - k + 1, nums[i - k + 1]));
        }
        return ans;
    }
}
