package class06;

import org.junit.Test;

/**
 * 题目1
 * 数组中所有数都异或起来的结果，叫做异或和
 * 给定一个数组arr，返回arr的最大子数组异或和
 */
public class Code01_MaxXOR {

    /**
     * 方法一，时间复杂度O(n^2)
     * 当考虑到子数组问题的时候，我们要条件反射想到以某个位置为结尾的子数组
     * 这样就转换为考虑前缀了
     * 我们以某个位置为结尾的子数组来考虑这个问题，然后把所有的结果都尝试一遍即可
     * @param arr   给定的数组
     * @return  返回的最大子数组异或和
     */
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //准备一个前缀异或和数组arr
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        //生成eor数组，eor[i]代表arr[0...i]的异或和
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        //遍历进行求解
        //外层for循环来遍历以j为结尾的子数组
        for (int j = 0; j < arr.length; j++) {
            //内层for循环来遍历以i为结尾的前缀
            for (int i = 0; i <= j; i++) {
                //依次尝试arr[0...j]、arr[1...j]...arr[i...j]...arr[j...j]
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }

    /**
     * 第二种解法
     * 我们在遍历到以i为结尾的子数组之前，将之前的前缀异或和放到前缀树当中
     * 这个前缀树我们从这个数的高位开始进行遍历构建
     * 然后对于遍历到的i，我们通过贪心的方式来解决问题
     * 例如当前0-4的总异或和为0110，而0-0为0100 0-1为0010 0-2为1001，0-4为1100，不计算前缀为0000，我们将后面这几个前缀和放到前缀树当中
     * 这样我们就能通过贪心的方式来解决问题了，我们就会从数的顶点出发，优先找第一位为1的，第二位为0的，第三位为0，第四位为1的
     * 当然，如果走到某一步发现没有找到理想的比特位，没有关系，接着走下去就是了
     * 最后这一趟记录结果，然后取每一趟的最大值即可
     * @param arr   给定的数组
     * @return  最大子数组异或和
     */
    public static int maxXorSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int xor = 0;
        int max = Integer.MIN_VALUE;
        //定义前缀树
        NumTrie numTrie = new NumTrie();
        //在遍历之前，先把0加进去
        numTrie.add(0);
        //然后再遍历
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];  // 0 ~ i
            max = Math.max(max, numTrie.maxXor(xor));
            numTrie.add(xor);
        }
        return max;
    }

    /**
     * 前缀树的Node结构
     * next[0] -> 0方向的路
     * next[1] -> 1方向的路
     * next[0]==null || next[1]==null 表示0或1方向没有东西，即没有下文
     */
    public static class Node {
        public Node[] nexts = new Node[2];
    }

    /**
     * 前缀树的实现
     */
    public static class NumTrie {
        //头节点
        public Node head = new Node();

        //把某个数字newNum加入到这棵前缀树里
        //num是一个32位的整数，所以加入的过程一共走32步
        public void add(int newNum) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                //从高位到低位，取出每一位的状态
                int path = ((newNum >> move) & 1);
                //无路创建，有路复用
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        public int maxXor(int sum) {
            Node cur = head;
            int ans = 0;
            for (int move = 31; move >= 0; move--) {
                int path = ((sum >> move) & 1);
                //期待的路（这里要注意，int类型是有符号的，当move==31的时候，需要把符号位考虑到，当符号位为0的时候，一定期望遇到0（1同理），此时期望为正数）
                int best = move == 31 ? path : (path ^ 1);
                //实际走的路
                best = cur.nexts[best] != null ? best : (best ^ 1);
                ans |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    @Test
    public void testMaxXorSubarray() {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = maxXorSubarray1(arr);
            int res = maxXorSubarray2(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
