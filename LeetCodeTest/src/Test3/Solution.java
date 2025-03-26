package Test3;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/9
 * @description TODO
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[] alpha = new int[128];
        char[] str = s.toCharArray();
        int left = -1;
        int right = 0;
        int max = 1;
        alpha[str[0]]++;
        while (right < n) {
            if (noSame(alpha)) {
                max = Math.max(max, right - left);
                right++;
                if (right == n) {
                    break;
                }
                alpha[str[right]]++;
            } else {
                left++;
                if (left == n) {
                    break;
                }
                alpha[str[left]]--;
            }
        }
        return max;
    }

    public static boolean noSame(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    }
}
