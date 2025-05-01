package Sorting;

import org.junit.Assert;
import org.junit.Test;

/**
 * SelectionSort
 *
 * @author 29096
 * @version 1.0
 * @date 2025/4/18
 * @description TODO
 */
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            //交换过程
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    @Test
    public void testSelectionSort() {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        selectionSort(arr);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}
