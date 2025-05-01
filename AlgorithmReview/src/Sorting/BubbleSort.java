package Sorting;

import org.junit.Assert;
import org.junit.Test;

/**
 * BubbleSort
 *
 * @author 29096
 * @version 1.0
 * @date 2025/4/18
 * @description TODO
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //这里遍历元素的范围是依次递减的
        for (int i = arr.length - 1; i > 0; i--) {
            //在递减的反唯后面进行冒泡交换
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    @Test
    public void testBubbleSort() {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        bubbleSort(arr);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}
