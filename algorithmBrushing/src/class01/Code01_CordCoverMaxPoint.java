package class01;

import java.util.Arrays;

/**
 * 题目1
 * 给定一个有序数组arr，代表坐标在X上的点
 * 给定一个正数K，代表绳子的长度
 * 返回绳子最多压中几个点？
 * 即使绳子边缘处盖在点也算盖住
 */
public class Code01_CordCoverMaxPoint {


    //使用贪心算法进行求解
    //我们从绳子的末尾看，因为末尾处盖住点也算，所以我们没必要让绳子的末尾端不盖住点
    //我们让绳子的尾端盖住点，看看左侧有多少个点在绳子的范围上即可
    //我们现在设绳子的长度为L，当前的末尾点为x，那么x-L就是绳子覆盖的范围，我们只需要看绳子中有多少个点<=x并且>=x-L即可。
    //而在这个有序数组当中，<=x并且>=x-L的所有点的个数我们可以通过二分查找的方式进行计算
    //此时的时间复杂度为O(n*logn)
    public static int maxPoint1(int[] arr, int L) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    public static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        //要找的是>=index的最左的值，所以这里要二分到死
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    //滑动窗口求解
    //使用两个指针记录绳子所覆盖的点的范围，向右滑动窗口即可
    //此时的时间复杂度为O(n)(遍历一遍数组即可)
    public static int maxPoint2(int[] arr, int L) {
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        while (left < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPoint2(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }

    }
}
