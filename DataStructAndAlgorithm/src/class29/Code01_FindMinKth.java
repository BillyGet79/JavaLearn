package class29;

public class Code01_FindMinKth {

    public static int minKth1(int[] arr, int index){
        //这个算法有问题。。。。
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if (index >= range[0] && index <= range[1]) {
                return pivot;
            } else if (index > range[1]) {
                L = range[1] + 1;
            } else {
                R = range[0] - 1;
            }
        }
        return arr[L];
    }


    //改写快排，时间复杂度O(n)
    public static int minKth2(int[] array, int k){
        int[] arr = copyArray(array);
        return process2(arr, 0, arr.length - 1, k - 1);
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i != ans.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    //arr 第k小的数
    //process2
    //arr[L...R]    范围上，如果排序的话，找位于index的数
    //index[L...R]
    public static int process2(int[] arr, int L, int R, int index){
        if (L == R){
            return arr[L];
        }
        //随机选出来一个数做划分值
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        //如果在范围中，直接返回
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]){
            //在范围左侧，递推调用左侧
            return process2(arr, L, range[0] - 1, index);
        } else {
            //在范围右侧，递归调用右侧
            return process2(arr, range[1] + 1, R, index);
        }
    }

    public static int[] partition(int[] arr, int L, int R, int pivot){
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more){
            if (arr[cur] < pivot){
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot){
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int a, int b){
        if (a != b){
            arr[a] = arr[a] ^ arr[b];
            arr[b] = arr[a] ^ arr[b];
            arr[a] = arr[a] ^ arr[b];
        }
    }

    public static int minKth3(int[] array, int index){
        int[] arr = copyArray(array);
        return bfprt(arr, 0, arr.length - 1, index - 1);
    }
    //这个方法求的就是第index小的数
    public static int bfprt(int[] arr, int L, int R, int index){
        if (L == R) {
            return arr[L];
        }
        //找到划分值
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]){
            return pivot;
        } else if (index < range[0]){
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }
    public static int medianOfMedians(int[] arr, int L, int R){
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        //这个就是在求这个中位数组的中位数，调用大方法，要仔细理解
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }
    //得到每个小组的中位数
    public static int getMedian(int[] arr, int L, int R){
        //插入排序
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }
    //因为最多只有五个数，所以用何种方法都一样，都属于常数级别的时间复杂度
    public static void insertionSort(int[] arr, int L, int R){
        for (int i = L + 1; i <= R; i++){
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--){
                swap(arr, j, j + 1);
            }
        }
    }
    //对数器
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 20;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
//            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans3 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
