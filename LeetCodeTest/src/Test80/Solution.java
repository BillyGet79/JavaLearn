package Test80;

/**
 * Solution
 *
 * @author 29096
 * @version 1.0
 * @date 2025/3/3
 * @description 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class Solution {
    /**
     * 注意是有序数组
     * 直接使用双指针，只不过需要两个变量来记录慢指针指向元素的当前状态
     * @param nums  数组
     * @return  剩余元素数量
     */
    public int removeDuplicates(int[] nums) {
        int lenAns = nums.length;
        int countNow = 1;
        int MemNum = nums[0];
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            //先看fast指向的元素
            if (nums[fast] != MemNum) {
                //如果fast指向元素与之前记忆的不符
                //那么就将当前元素赋值到slow上，然后slow与fast向右移动
                //并且更新countNow与MemNum
                countNow = 1;
                MemNum = nums[fast];
                nums[slow] = nums[fast];
                slow++;
                fast++;
            } else {    //相等的情况下
                //看countNow
                if (countNow != 2) {
                    //不等于2的情况下，那么当前元素就可以保留，fast指针直接右移即可
                    //此时countNow++
                    fast++;
                    countNow++;
                } else {
                    //等于2的情况下，就说明该舍弃这个元素了，让slow指向当前的fast，然后fast向右移动
                    slow = fast;
                    fast++;
                    lenAns--;
                }
            }
        }
        return lenAns;
    }
}
