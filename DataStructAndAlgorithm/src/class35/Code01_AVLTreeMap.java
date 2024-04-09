package class35;

public class Code01_AVLTreeMap {

    public static class AVLNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public AVLNode<K, V> l;
        public AVLNode<K, V> r;
        public int h;

        public AVLNode(K key, V value) {
            k = key;
            v = value;
            h = 1;
        }
    }
    public static class AVLTreeMap<K extends Comparable<K>, V> {
        //整个树的根节点
        private AVLNode<K, V> root;
        //一共加入了几个节点
        private int size;
        public AVLTreeMap() {
            root = null;
            size = 0;
        }
        //右旋操作（重点）
        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            //记一下它的左孩子
            AVLNode<K, V> left = cur.l;
            //让左孩子的右树挂在当前节点的左边
            cur.l = left.r;
            //当前节点挂在左孩子的右边
            left.r = cur;
            //当前节点的高度取自己左右两棵树的高度的最大值+1
            cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
            //同时left也要更新
            left.h = Math.max((left.l != null ? left.l.h : 0), (left.r != null ? left.r.h : 0)) + 1;
            //将left返回，left将当前节点代替了
            return left;
        }
        //左旋操作（重点）
        private AVLNode<K,V> leftRotate(AVLNode<K, V> cur) {
            //记一下它的右孩子
            AVLNode<K, V> right = cur.r;
            //让右孩子的左树挂在当前节点的右边
            cur.r = right.l;
            //当前节点挂在右孩子的左边
            right.l = cur;
            //当前节点的高度取自己左右两棵树的高度的最大值+1
            cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
            //同时right也要更新，注意顺序，一定要先更新cur的
            right.h = Math.max((right.l != null ? right.l.h : 0), (right.r != null ? right.r.h : 0)) + 1;
            //将right返回，right将当前节点代替了
            return right;
        }
        //（重点）调整方法
        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            //获得两个子树的高度
            int leftHeight = cur.l != null ? cur.l.h : 0;
            int rightHeight = cur.r != null ? cur.r.h : 0;
            //如果不平衡就直接进入判断
            if (Math.abs(leftHeight - rightHeight) > 1) {
                //左树高度大于右树高度，必定为L型
                if (leftHeight > rightHeight) {
                    //找左树的左子树高度和右子树高度
                    int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                    int leftRightHeight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
                    if (leftLeftHeight >= leftRightHeight) {
                        //LL型处理
                        cur = rightRotate(cur);
                    } else {
                        //LR型处理
                        cur.l = leftRotate(cur.l);
                        cur = rightRotate(cur);
                    }
                } else {
                    //右树高度大于左树高度，必定为R型
                    int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
                    int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                    if (rightRightHeight >= rightLeftHeight) {
                        //RR型处理
                        cur = leftRotate(cur);
                    } else {
                        //RL型处理
                        cur.r = rightRotate(cur.r);
                        cur = leftRotate(cur);
                    }
                }
            }
            return cur;
        }
        //添加元素
        //上游检查不会有相同的key
        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            //如果当前节点是头节点为空，那么直接new一个
            //如果当前节点是遍历到的为空，即遍历到了叶子节点，那么也直接new一个向上返回
            if (cur == null) {
                return new AVLNode<K, V>(key, value);
            } else {
                if (key.compareTo(cur.k) < 0) {
                    //传入的值比当前节点值小，那么直接插在左树
                    //至于插在哪里，继续向下调用
                    cur.l = add(cur.l, key, value);
                } else {
                    cur.r = add(cur.r, key, value);
                }
                //因为当前节点加入节点了，所以要调整高度
                cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
                //调整当前节点
                //因为可能会换头节点，所以一定要带返回值
                return maintain(cur);
            }
        }
        //删除元素
        //上游判断节点是否存在
        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
            if (key.compareTo(cur.k) > 0) {
                cur.r = delete(cur.r, key);
            } else if (key.compareTo(cur.k) < 0) {
                cur.l = delete(cur.l, key);
            } else {
                //下面是四种情况的处理
                if (cur.l == null && cur.r == null) {
                    cur = null;
                } else if (cur.l == null && cur.r != null) {
                    cur = cur.r;
                } else if (cur.l != null && cur.r == null) {
                    cur = cur.l;
                } else {
                    //既有左孩子又有右孩子的情况
                    AVLNode<K, V> des = cur.r;
                    //找到右树上的最左节点
                    while (des.l != null) {
                        des = des.l;
                    }
                    //将右树上的最左节点删除
                    cur.r = delete(cur.r, des.k);
                    //让这个最左节点把当前节点代替
                    des.l = cur.l;
                    des.r = cur.r;
                    cur = des;
                }
            }
            if (cur != null) {
                cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            }
            return maintain(cur);
        }
        public int size() {
            return size;
        }
        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.k) == 0 ? true : false;
        }
        //找到小于等于key最右的数
        private AVLNode<K,V> findLastIndex(K key) {
            AVLNode<K, V> pre = root;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.k) == 0) {
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return pre;
        }
        //找到大于等于key最左的数
        private AVLNode<K, V> findLastNoSmallIndex(K key) {
            AVLNode<K, V> ans = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    ans = cur;
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return ans;
        }
        private AVLNode<K, V> findLastNoBigIndex(K key) {
            AVLNode<K, V> ans = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                } else {
                    ans = cur;
                    cur = cur.r;
                }
            }
            return ans;
        }
        //添加元素
        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                lastNode.v = value;
            } else {
                size++;
                root = add(root, key, value);
            }
        }
        //删除元素
        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                size--;
                root = delete(root, key);
            }
        }
        //得到某一个key的value
        public V get(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                return lastNode.v;
            }
            return null;
        }
        //找到头Key
        public K firstKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.l != null) {
                cur = cur.l;
            }
            return cur.k;
        }
        //找到尾key
        public K lastKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.r != null) {
                cur = cur.r;
            }
            return cur.k;
        }
        //
        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.k;
        }
        //
        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.k;
        }
    }
}
