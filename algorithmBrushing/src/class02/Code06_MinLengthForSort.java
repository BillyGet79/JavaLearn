package class02;

/**
 * 题目6
 * 给定一个数组arr，只能对arr中的一个子数组排序，
 * 但是想让arr整体都有序
 * 返回满足这一设定的子数组中，最短的是多长
 * 本题测试链接 : <a href="https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/">581.最短无序连续子数组</a>
 */
public class Code06_MinLengthForSort {

    //整体算法的执行流程如下：
    //1、定义一个指针，指向数组第二个元素
    //2、定义一个变量leftMax，记录当前指针指向的元素的左侧最大元素，初始值为数组第一个元素
    //3、定义一个指针index
    //4、通过指针遍历这个数组：
    //  如果当前指向的元素>=leftMax，更新leftMax，指针向后移动
    //  如果当前指向的元素<leftMax，让index指针指向当前元素，指针向后移动
    //5、定义指针rightMin，指针index，通过相同的方式让数组从后往前再遍历一遍
    //6、right到left这个范围内的数组就是我们要找的数组
    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1 ) {
            return 0;
        }
        //找到left指针的值
        int left = leftTraversal(nums);
        //找到right指针的值1
        int right = rightTraversal(nums);
        return right == left ? 0 : left - right + 1;
    }

    public static int leftTraversal(int[] nums) {
        //记录指针左侧的最大的值
        int leftMax = nums[0];
        //这里可以定义为0，不过在rightTraversal中也必须定义为0
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= leftMax) {
                leftMax = nums[i];
            } else {
                index = i;
            }
        }
        return index;
    }

    public static int rightTraversal(int[] nums) {
        int rightMin = nums[nums.length - 1];
        int index = 0;
        for (int i = nums.length - 2; i >= 0 ; i--) {
            if (rightMin >= nums[i]) {
                rightMin = nums[i];
            } else {
                index = i;
            }
        }
        return index;
    }

    //我们通过leftTraversal方法得到的下标表明，下标右侧的所有数据全部都是有序的
    //而当我们通过rightTraversal方法得到的下标表明，下标左侧的所有数据全部都是有序的
    //所以两个下标中间的数据一定就是无序的，将其进行排序即可
    //时间复杂度O(n)，空间复杂度O(1)
}
