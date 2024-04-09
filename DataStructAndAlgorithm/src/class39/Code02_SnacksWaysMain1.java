package class39;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Code02_SnacksWaysMain1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int bag = (int) in.nval;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            long ways = ways(arr, bag);
            out.println(ways);
            out.flush();
        }
    }

    public static long ways(int[] arr, int bag) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] <= bag ? 2 : 1;
        }
        //找中间值
        int mid = (arr.length - 1) >> 1;
        //找左侧的所有sum
        TreeMap<Long, Long> lmap = new TreeMap<>();
        long ways = func(arr, 0, 0, mid, bag, lmap);
        //找右侧的所有sum，并且累加
        TreeMap<Long, Long> rmap = new TreeMap<>();
        ways += func(arr, mid + 1, 0, arr.length - 1, bag, rmap);
        //定义前缀和map，并计算
        TreeMap<Long, Long> rpre = new TreeMap<>();
        long pre = 0;
        for (Map.Entry<Long, Long> entry : rmap.entrySet()) {
            pre += entry.getValue();
            rpre.put(entry.getValue(), pre);
        }
        //遍历左map，通过有序表的功能找到小于等于bag-lweight的最大值key，然后进行ways计算
        for (Map.Entry<Long, Long> entry : lmap.entrySet()) {
            long lweight = entry.getKey();
            long lways = entry.getValue();
            Long floor = rpre.floorKey(bag - lweight);
            if (floor != null) {
                long rways = rpre.get(floor);
                ways += lways;
            }
        }
        //我们定义的func是不包含一个也不拿的情况的，所以最后要算上这种情况
        return ways + 1;
    }

    //从index出发，到end结束
    //零食自由选择，出来的所有累加和，不能超过bag，每一种累加和对应的方法数，填在map里。
    //最后不能什么货都没选
    public static long func(int[] arr, int index, long sum, int end, int bag, TreeMap<Long, Long> map) {
        if (sum > bag) {
            return 0;
        }
        if (index > end) {
            //sum
            if (sum != 0) {
                if (!map.containsKey(sum)) {
                    map.put(sum, 1L);   //这个1L代表的是Long类型的1
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
                return 1;
            } else {
                return 0;
            }
        }
        //还有货
        //1.不要当前index位置的货
        long ways = func(arr, index + 1, sum, end, bag, map);
        //2.要当前index位置的货
        ways += func(arr, index + 1, sum + arr[index], end, bag, map);
        return ways;
    }
}
