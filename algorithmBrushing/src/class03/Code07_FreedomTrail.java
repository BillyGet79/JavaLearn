package class03;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 题目7
 * 电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。
 * 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * 1.您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 2.如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 * 本题测试链接 : <a href="https://leetcode.cn/problems/freedom-trail/">...</a>
 */
public class Code07_FreedomTrail {

    //我们首先想到的就是模拟这个转盘的轮转
    //所以我们自然想到了用哈希表来存储转盘上每一个字符的下标
    //这样我们就能通过哈希表来获取道每一个字符所在的下标
    //当然，这个下标可以是多个，所以哈希表的value要用一个list集合存储
    //我们要求的是总体的步数最小，所以我们不能贪心走局部最小
    //所以我们这里需要尝试动态规划

    public static int findRotateSteps1(String r, String k) {
        char[] ring = r.toCharArray();
        int rsize = ring.length;
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < rsize; i++) {
            if (!map.containsKey(ring[i])) {
                map.put(ring[i], new ArrayList<>());
            }
            map.get(ring[i]).add(i);
        }
        return process1(0, 0, k.toCharArray(), map, rsize);
    }

    /**
     * 尝试方法
     * @param preButton 当前的轮盘指针在的地方，及上一个字符检索后的key在轮盘上的下标
     * @param index  当前匹配的key的下标
     * @param str   所要匹配的key
     * @param map   所有字符的所在位置   key：字符  value：所在下标
     * @param N 电话机的大小
     * @return
     */
    public static int process1(int preButton, int index, char[] str, HashMap<Character, ArrayList<Integer>> map, int N) {
        if (index == str.length) {
            return 0;
        }
        //还有字符需要搞定
        char cur = str[index];
        ArrayList<Integer> nextPositions = map.get(cur);
        int ans = Integer.MAX_VALUE;
        for (int next : nextPositions) {
            int cost = dial(preButton, next, N) + 1 + process1(next, index + 1, str, map, N);
            ans = Math.min(ans, cost);
        }
        return ans;
    }

    //对于i1位置拨到i2位置，怎样拨最省
    //size为表盘大小
    public static int dial(int i1, int i2, int size) {
        return Math.min(Math.abs(i1 - i2), Math.min(i1, i2) + size - Math.max(i1, i2));
    }

    public static int findRotateSteps2(String r, String k) {
        char[] ring = r.toCharArray();
        int rsize = ring.length;
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < rsize; i++) {
            if (!map.containsKey(ring[i])) {
                map.put(ring[i], new ArrayList<>());
            }
            map.get(ring[i]).add(i);
        }
        int[][] dp = new int[rsize + 1][k.length() + 1];
        for (int i = 0; i < rsize + 1; i++) {
            for (int j = 0; j < k.length() + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(0, 0, k.toCharArray(), map, rsize, dp);
    }

    public static int process2(int preButton, int index, char[] str, HashMap<Character, ArrayList<Integer>> map, int N, int[][] dp) {
        if (dp[preButton][index] != -1) {
            return dp[preButton][index];
        }
        if (index == str.length) {
            return 0;
        }
        //还有字符需要搞定
        char cur = str[index];
        ArrayList<Integer> nextPositions = map.get(cur);
        int ans = Integer.MAX_VALUE;
        for (int next : nextPositions) {
            int cost = dial(preButton, next, N) + 1 + process2(next, index + 1, str, map, N, dp);
            ans = Math.min(ans, cost);
        }
        dp[preButton][index] = ans;
        return ans;
    }
}
