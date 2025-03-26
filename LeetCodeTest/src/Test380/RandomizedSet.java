package Test380;

import java.util.*;

/**
 * RandomizedSet
 * 使用变长数组+哈希表来解决这个问题
 * 哈希表存储key为值，value为变长数组下标
 * @author 29096
 * @version 1.0
 * @date 2025/3/25
 * @description TODO
 */
public class RandomizedSet {

    private List<Integer> list;
    private Random random;
    private Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<>();
        random = new Random();
        map = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        int index = list.size();
        list.add(index, val);
        map.put(val, index);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        Integer index = map.get(val);
        Integer value = list.get(list.size() - 1);
        map.put(value, index);
        list.set(index, value);
        map.remove(val);
        list.removeLast();
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
