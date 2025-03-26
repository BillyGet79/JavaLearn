package Test146;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/10
 * @description TODO
 */
public class Solution {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        list.add(cache.get(4));
        list.add(cache.get(3));
        list.add(cache.get(2));
        list.add(cache.get(1));
        cache.put(5, 5);
        list.add(cache.get(1));
        list.add(cache.get(2));
        list.add(cache.get(3));
        list.add(cache.get(4));
        list.add(cache.get(5));
        System.out.println(list);
    }
}
