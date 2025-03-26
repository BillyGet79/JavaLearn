package Test567;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/9
 * @description TODO
 */
public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int left = 0;
        int right = len1 - 1;
        int[] alpha1 = new int[26];
        int[] alpha2 = new int[26];
        for (int i = 0; i < len1; i++) {
            alpha1[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < right; i++) {
            alpha2[s2.charAt(i) - 'a']++;
        }
        while (right < len2) {
            alpha2[s2.charAt(right) - 'a']++;
            if (!isSame(alpha1, alpha2)) {
                alpha2[s2.charAt(left) - 'a']--;
                left++;
                right++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean isSame(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkInclusion("ab", "eidbaooo"));
    }
}
