package class25;

import java.util.Stack;

// 测试链接：https://leetcode.cn/problems/maximal-rectangle/
public class Code04_MaximalRectangle {

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        //保存上一行的元素值
        int[] temp = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                temp[j] = matrix[i][j] == 0 ? 0 : temp[j] + matrix[i][j];
            }
            ans = Math.max(ans, largestRectangleArea(temp));
        }
        return ans;
    }
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0){
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                int index = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                int largeDistance = i - leftLessIndex - 1;
                ans = Math.max(ans, heights[index]* largeDistance);
            }
            stack.push(i);
        }
        int N = heights.length;
        while (!stack.isEmpty()){
            int index = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            int largeDistance = N - 1 - leftLessIndex;
            ans = Math.max(ans, heights[index]* largeDistance);
        }
        return ans;
    }
}
