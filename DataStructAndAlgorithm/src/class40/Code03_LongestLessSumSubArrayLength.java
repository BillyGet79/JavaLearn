package class40;

public class Code03_LongestLessSumSubArrayLength {

    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //先将minSum与minSumEnd求出来
        int[] minSum = new int[arr.length];
        int[] minSumEnds = new int[arr.length];
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnds[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSum[i + 1] < 0) {
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnds[i] = i;
            }
        }
        //结尾指针
        int end = 0;
        //记录当前总和
        int sum = 0;
        //记录当前最长长度
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            //先进行end指针扩充
            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumEnds[end] + 1;
            }
            //记录当前扩充的长度
            ans = Math.max(ans, end - i);
            //如果end指针大于当前的下标的时候，将当前的arr值从sum中减去
            if (end > i) {
                sum -= arr[i];
            } else {
                //如果到了这一步，那么就说明当前的下标与当前标记的结尾相同，这个时候结尾向上+1
                //同时，如果到了这一步，sum一定变为0了
                end = i + 1;
            }
        }
        return ans;
    }


    //用一个比较暴力的方法来解决
    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    //对数器
    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
