package Test275;

public class Solution {
    //二分查找方法解决问题
    //先写一个简单的，使用线性遍历实现
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0){
            return 0;
        }
        int N = citations.length;
        int left = 0;
        int right = N - 1;
        while (left <= right){
            int mid = left + (right - left) >> 1;
            if (citations[mid] > N - mid){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return N - right;
    }
}
