package class04;

/**
 * 题目6
 * 生成长度为size的达标数组，什么叫达标？
 * 达标：对于任意的i<k<j，满足[i]+[j]!=[k]*2
 * 给定一个正数size，返回长度为size的达标数组
 */
public class Code06_MakeNo {

    /**
     * 我们现在假设i,k,j指向的数分别为a,b,c
     * 我们需要满足的条件是a + c != b * 2
     * 在此基础上，如果将a,b,c都乘上2，那么这个不等式必然成立
     * 在此基础上，所有数都+1，那么这个不等式也必然成立
     * 我们现在假设一个长度为6的数组，前三个数分别为2a, 2b, 2c，后三个数分别为2a+1, 2b+1, 2c+1
     * 那么数组前一半一定满足条件，后一半一定满足条件
     * 如果考虑全数组，由于前半部分是偶数，后半部分是奇数，所以不管怎么匹配都是满足不等式条件的
     * 在这个基础上，我们有了这道题的解法
     * 输入的长度为size，我们除2向上取整，用上面的规律来说，我们只需要找到除2的一个种子，我们就可以得到完整的达标的数组（多出去的部分砍掉就可以了）
     * 然后对于这个种子，我们可以继续除2向上取整，向下递归去做就行
     * @param size
     * @return
     */
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        int[] ans = new int[size];
        int index = 0;
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 - 1;
        }
        for (int i = 0; index < size; index++, i++) {
            ans[index] = base[index] * 2;
        }
        return ans;
    }
}





