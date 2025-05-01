package BitCompute;

/**
 * BitCompute
 *
 * @author 29096
 * @version 1.0
 * @date 2025/5/1
 * @description TODO
 */
public class BitCompute {
    /**
     * 一个数组中，只有一个数，出现过奇数次，其余数都是偶数次，找出这个数
     * @param arr
     * @return
     */
    public int getOddTimesNum1(int[] arr) {
        //将所有的数异或起来即可
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        return eor;
    }

    /**
     * 一个数组中，有两个数，出现过奇数次，其余数都是偶数次，找出这个数
     * @param arr
     */
    public int[] getOddTimesNum2(int[] arr) {
        //先通过整体异或运算将其他出现过偶数次的数排除掉
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        //找到eor的最右侧的一
        int rightOne = eor & (-eor);
        int onlyOne = 0;
        //遍历，找到为1的那一位的数，依旧通过异或排除出现偶数次的数
        for (int i = 0; i < arr.length; i++) {
            //只有rightOne指出的那一位为1的情况下才参与运算
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        return new int[]{onlyOne, eor ^ onlyOne};
    }

    /**
     * 一个数组中，只有一种数出现了K次，其余的数都出现了M次，并且满足`1 <= K < M`，返回这种数
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public int onlyKTimes(int[] arr, int k, int m) {
        int[] t = new int[32];
        //遍历每个数
        for (int num : arr) {
            //对于每个数，统计其比特位
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    t[i]++;
                }
            }
        }
        //统计过后，如果当前每个比特位统计的数字%m为0，则就是m个数
        //如果%m不为0.那么该位就是我们要找到的数在这个比特位为1
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                res |= (1 << i);
            }
        }
        return res;
    }
}
