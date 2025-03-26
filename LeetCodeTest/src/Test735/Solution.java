package Test735;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

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
     * 使用栈解决问题
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            boolean alive = true;
            while (alive && asteroid < 0 && !stack.isEmpty() && stack.peek() > 0) {
                alive = stack.peek() < -asteroid;
                if (stack.peek() <= -asteroid) {
                    stack.pop();
                }
            }
            if (alive) {
                stack.push(asteroid);
            }
        }
        int size = stack.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0 ; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }



    public static void main(String[] args) {
        int[] asteroids = new int[]{-2,-1,1,2};
        System.out.println(Arrays.toString(new Solution().asteroidCollision(asteroids)));
    }
}
