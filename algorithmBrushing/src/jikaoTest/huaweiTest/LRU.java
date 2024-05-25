package jikaoTest.huaweiTest;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

public class LRU {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new PrintWriter(System.out));

        in.nextToken();
        int capacity = (int) in.nval;
        in.nextToken();
        int count = (int) in.nval;
        LRUCache cache = new LRUCache(capacity);
        for (int i = 0; i < count; i++) {
            in.nextToken();
            char c = in.sval.charAt(0);
            in.nextToken();
            int num = (int) in.nval;
            if (c == 'A') {
                cache.put(num, 1);
            }
            if (c == 'D') {
                cache.remove(num);
            }
            if (c == 'Q') {
                
            }
        }
    }

}
