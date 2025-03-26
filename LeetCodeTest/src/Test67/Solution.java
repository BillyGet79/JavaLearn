package Test67;

import java.util.Stack;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/9
 * @description TODO
 */
public class Solution {

    public String addBinary(String a, String b) {
        //将两个字符串转为字符数组
        char[] strA = a.toCharArray();
        char[] strB = b.toCharArray();
        Stack<Character> stack = new Stack<>();
        //从后向前遍历
        int indexA = strA.length - 1;
        int indexB = strB.length - 1;
        int p = 0;  //保存进位
        while (indexA >= 0 && indexB >= 0) {
            //获取两个字符对应的值
            int m = strA[indexA] - '0';
            int n = strB[indexB] - '0';
            int sum = (m + n + p) % 2;
            //这种情况下说明有进位，要记录
            if (m + n + p >= 2) {
                p = 1;
            } else {
                p = 0;
            }
            stack.push((char) (sum + '0'));
            indexA--;
            indexB--;
        }
        //如果lengthA遍历完了，那么lengthB一定没有遍历完
        if (indexA < 0) {
            while (indexB >= 0) {
                int m = strB[indexB] - '0';
                int sum = (m + p) % 2;
                if (m + p >= 2) {
                    p = 1;
                } else {
                    p = 0;
                }
                stack.push((char) (sum + '0'));
                indexB--;
            }
        }
        if (indexB < 0) {
            while (indexA >= 0) {
                int m = strA[indexA] - '0';
                int sum = (m + p) % 2;
                if (m + p >= 2) {
                    p = 1;
                } else {
                    p = 0;
                }
                stack.push((char) (sum + '0'));
                indexA--;
            }
        }
        //观察进位，如果进位不为0，则将进位添加进去
        if (p == 1) {
            stack.push('1');
        }
        //最后从栈弹出得到字符串
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.addBinary("1", "111"));
    }
}
