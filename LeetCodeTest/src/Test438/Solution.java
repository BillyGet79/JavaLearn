package Test438;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/9
 * @description TODO
 */
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> res = new ArrayList<>();
        int len1 = s.length(), len2 = p.length();
        if (len1 < len2) {
            return new ArrayList<>();
        }
        int[] alphaS = new int[26];
        int[] alphaP = new int[26];
        int left = 0;
        int right = len2 - 1;
        for (int i = 0; i < len2; i++) {
            alphaP[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i < right; i++) {
            alphaS[s.charAt(i) - 'a']++;
        }
        while (right < len1) {
            alphaS[s.charAt(right) - 'a']++;
            if (isSame(alphaS, alphaP)) {
                res.add(left);
            }
            alphaS[s.charAt(left) - 'a']--;
            left++;
            right++;

        }
        return res;
    }

    public static boolean isSame(int[] alphaS, int[] alphaP) {
        for (int i = 0; i < 26; i++) {
            if (alphaP[i] != alphaS[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
    }
}
