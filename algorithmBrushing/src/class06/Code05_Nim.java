package class06;

import org.junit.Test;

/**
 * Nim博弈问题
 * 博弈双方通过先后手的方式对一个数组中的所有“石头堆”拿取石头，每次必须选择一个“石头堆”，且必须至少拿一个
 * 给定一个数组，输出是先手赢还是后手赢
 */
public class Code05_Nim {

    /**
     * 解法：将数组所有元素求异或和，如果!=0，则先手赢，否则后手赢
     * 我们通过二进制的方式考虑这个问题：
     * 如果异或和不为0，那么先手可以通过改变数组中的一个数将异或和变为0
     * 这样后手无论怎么拿取，异或和一定就不为0了
     * 这样先手再次将其变为0，这样先手一定会赢
     * 如果异或和为0，那么先手拿完后异或和一定就不为0了，后手就可以复刻先手异或和不为0的情况
     * 这样后手一定会赢
     * @param nums  目标数组
     * @return  返回值为true时，表示先手赢，否则后手赢
     */
    public static boolean Nim(int[] nums) {
        //对所有元素求异或和
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        return eor != 0;
    }

    @Test
    public void testNim() {
        boolean res = Nim(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        if (res) {
            System.out.println("先手赢");
        } else {
            System.out.println("后手赢");
        }
    }
}
