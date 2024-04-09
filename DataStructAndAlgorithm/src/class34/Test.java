package class34;

import class33.Hash;

public class Test {

    public static String[] diffStr(int N) {
        String[] ans = new String[N];
        for (int i = 0; i < N; i++) {
            ans[i] = "zuochengyun" + String.valueOf(i) + "zuochengyunzuochengyun" + String.valueOf(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 1080;
        String[] strs = diffStr(N);

        int[] count = new int[10];

        //这个哈希函数有问题
        //改进一下哈希函数即可
        for (String str : strs) {
            int hashCode = str.hashCode();
            count[Math.abs(hashCode % 10)]++;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ":" + count[i]);
        }

    }

}
