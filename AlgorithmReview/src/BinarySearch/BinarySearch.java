package BinarySearch;

/**
 * Main
 *
 * @author 29096
 * @version 1.0
 * @date 2025/4/18
 * @description TODO
 */
public class BinarySearch {
    /**
     * 查找一个数是否存在
     * @param sortedArr
     * @param num
     * @return
     */
    public boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = (L + R) / 2;
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] < num) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return sortedArr[L] == num;
    }

    /**
     * 在arr上，找满足>=value的最左位置，返回的是数组下标
     * @param arr
     * @param value
     * @return
     */
    public int nearestRightIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    /**
     *
     * @param arr
     * @param value
     * @return
     */
    public int nearestLeftIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] <= value) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    /**
     * 找局部最小值（返回一个即可，不需要全部找到）
     * 思考方式如下：
     * 如果左端点比左端点的下一个值小，那么左端点就直接是一个局部最小值，直接返回即可（右端点同理）
     * 如果上述情况不满足，可以想象一下一个曲线的左端点递减，右端点附近递增，那么这个中间一定有极值点，这种情况下直接使用二分法进行下一步判断即可
     * @param arr
     * @return
     */
    public int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] > arr[arr.length - 2]) {
            return arr.length - 1;
        }
        //当执行完上述步骤之后，这个区域内一定有局部最小值
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = (L + R) / 2;
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] < arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return L;
    }

}
