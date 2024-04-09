package class25;

import java.util.Stack;

// 测试链接：https://leetcode.cn/problems/count-submatrices-with-all-ones
public class Code05_CountSubmatricesWithAllOnes {
    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0){
            return 0;
        }
        int ans = 0;
        int[] temp = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                temp[j] = mat[i][j] == 0 ? 0 : temp[j] + 1;
            }
            ans += countFromBottom(temp);
        }
        return ans;
    }

    public static int countFromBottom(int[] height){
        if (height == null || height.length == 0){
            return 0;
        }
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]){
                int index = stack.pop();
                if (height[index] > height[i]){
                    int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                    int len = i - leftLessIndex - 1;
                    int down = Math.max(leftLessIndex == -1 ? 0 : height[leftLessIndex], height[i]);
                    sum += (len * (len + 1) / 2) * (height[index] - down);
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int index = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            int len = height.length - 1 - leftLessIndex;
            int down = leftLessIndex == -1 ? 0 : height[leftLessIndex];
            sum += (len * (len + 1) / 2) * (height[index] - down);
        }
        return sum;
    }
}
