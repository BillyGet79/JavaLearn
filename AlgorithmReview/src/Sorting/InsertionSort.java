package Sorting;

import org.junit.Assert;
import org.junit.Test;

/**
 * InsertionSort
 *
 * @author 29096
 * @version 1.0
 * @date 2025/4/18
 * @description TODO
 */
public class InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //想象扑克牌的排序方式。不过我们在实际操作扑克牌的时候会直接将其插入到指定地点
        //这里我们需要通过交换慢慢的将其放到指定地点
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

    @Test
    public void testInsertionSort() {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        insertionSort(arr);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

}
