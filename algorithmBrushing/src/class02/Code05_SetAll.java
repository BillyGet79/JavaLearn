package class02;

import java.util.HashMap;

/**
 * 题目5
 * 定义一个特殊的hashmap，实现put、get以及setAll方法
 * 其中put和get方法与原hashmap相同，setAll方法为将所有的键值对的值全部改为传入的值
 * 并且这三个方法的时间复杂度都为O(1)
 */
public class Code05_SetAll {

    public static class MyValue<V> {
        public V value;
        public long time;

        public MyValue(V value, long time) {
            this.value = value;
            this.time = time;
        }
    }

    public static class MyHashMap<K, V> {
        private HashMap<K, MyValue<V>> map;
        private long time;
        private MyValue<V> setAll;

        public MyHashMap() {
            map = new HashMap<>();
            time = 0;
            setAll = new MyValue<>(null, -1);
        }

        public void put(K key, V value) {
            map.put(key, new MyValue<>(value, time++));
        }

        public void setAll(V value) {
            setAll = new MyValue<>(value, time++);
        }

        public V get(K key) {
            if (!map.containsKey(key)) {
                return null;
            }
            if (map.get(key).time > setAll.time) {
                return map.get(key).value;
            } else {
                return setAll.value;
            }
        }
    }

}
