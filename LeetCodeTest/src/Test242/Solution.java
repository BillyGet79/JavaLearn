package Test242;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/25
 * @description TODO
 */
public class Solution {
    /**
     * 用一个数组来保存s串的拥有的字母
     * 然后遍历t时对应位--1即可
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.equals(t)) {
            return false;
        }
        int[] alphaNum = new int[26];
        for (char c : s.toCharArray()) {
            alphaNum[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            alphaNum[c - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (alphaNum[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isAnagram("a", "a"));
    }
}
