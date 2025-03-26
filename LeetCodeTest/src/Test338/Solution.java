package Test338;

import java.util.Arrays;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/9
 * @description TODO
 */
public class Solution {
    public int[] countBits(int n) {
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 31; j >= 0; j--) {
                int temp = (i >> j) & 1;
                if (temp == 1) {
                    count++;
                }
            }
            arr[i] = count;
        }
        return arr;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.countBits(5)));
    }
}
