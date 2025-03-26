package Test953;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/25
 * @description TODO
 */
public class Solution {

    public boolean isAlienSorted(String[] words, String order) {
        //先遍历order，将其组织成相对位置数组
        int[] alphaOrder = new int[26];
        for (int i = 0; i < order.length(); i++) {
            alphaOrder[order.charAt(i) - 'a'] = i;
        }
        //然后遍历words
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            String wordBefore = words[i - 1];
            if (!BigThanBefore(wordBefore, word, alphaOrder)) {
                return false;
            }
        }
        return true;
    }

    public static boolean BigThanBefore(String a, String b, int[] alphaOrder) {
        char[] strA = a.toCharArray();
        char[] strB = b.toCharArray();
        for (int i = 0, j = 0; i < strA.length && j < strB.length; i++, j++) {
            if (alphaOrder[strA[i] - 'a'] < alphaOrder[strB[j] - 'a']) {
                return true;
            } else if (alphaOrder[strA[i] - 'a'] == alphaOrder[strB[j] - 'a']) {
                continue;
            } else {
                return false;
            }
        }
        return strA.length <= strB.length;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[]{"apple","app"};
        String order = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(solution.isAlienSorted(words, order));
    }
}
