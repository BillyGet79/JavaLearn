package Test318;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/9
 * @description TODO
 */
public class Solution {

    public static class WordBit {
        public int bit;
        public int length;
        public WordBit() {
            bit = 0;
            length = 0;
        }
    }

    public int maxProduct(String[] words) {
        WordBit[] bits = new WordBit[words.length];
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            bits[i] = new WordBit();
            for (char a : chars) {
                bits[i].bit |= (1 << (a - 'a'));
                bits[i].length++;
            }
        }
        int max = 0;
        for (int i = 0; i < bits.length; i++) {
            for (int j = i + 1; j < bits.length; j++) {
                if ((bits[i].bit & bits[j].bit) == 0) {
                    max = Math.max(max, bits[i].length * bits[j].length);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"abcw","baz","foo","bar","fxyz","abcdef"};
        System.out.println(solution.maxProduct(words));
    }
}
