package Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Solution
 * 
 * @author 29096
 * @date 2025/3/26
 * @version 1.0
 * @description TODO
 */
public class Solution {

    public static class log {
        String data;
        String ip;
        String url;
        String return_code;
    }
    public static class P {
        String ip;
        int count;
    }
    public static P[] selectTopThree(log[] accessLog) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < accessLog.length; i++) {
            if (!map.containsKey(accessLog[i].ip)) {
                map.put(accessLog[i].ip, 0);
            }
            map.put(accessLog[i].ip, map.get(accessLog[i].ip) + 1);
        }
        ArrayList<P> list = new ArrayList<>();
        map.forEach((String ip, Integer count) -> {
            P tmp = new P();
            tmp.ip = ip;
            tmp.count = count;
        });
        list.sort(new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o2.count - o1.count;
            }
        });
        P[] res = new P[3];
        res[0] = list.get(0);
        res[1] = list.get(1);
        res[2] = list.get(2);
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    }
}
