package Test125;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/2/28
 * @description 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 *
 * 字母和数字都属于字母数字字符。
 *
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 */
public class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        char[] str = s.toCharArray();
        int left = 0, right = str.length - 1;
        while (left <= right) {
            if (!isAlphaAndNum(str[left])) {
                left++;
            } else if (!isAlphaAndNum(str[right])) {
                right--;
            } else if (str[left] == str[right]) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphaAndNum(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    public static void main(String[] args) {
        String s = "0P";
        System.out.println(new Solution().isPalindrome(s));
    }
}
