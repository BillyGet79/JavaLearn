package Test;

import java.util.ArrayList;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/4/28
 * @description TODO
 */
public class Solution {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        int tmp = 50;
        for (int i = 0; i < tmp; i++) {
            list.add(i);
            if (tmp <= 100) {
                tmp++;
            }
        }
        System.out.println(list);
    }


}
