package class03;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 题目2
 * 只由小写字母(a~z)组成的一批字符串
 * 都放在字符类型的数组String[] arr中
 * 如果其中某两个字符串所含有的字符种类完全一样
 * 就将两个字符串算作一类
 * 比如：baacbba和bac就算做一类
 * 返回arr中有多少类
 */
public class Code02_HowManyTypes {

    public static int types1(String[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //定义一个大小为26的布尔数组，来表示每一个字符串出现过的字符
        boolean[] occurLetter = new boolean[26];
        //这里面值默认都是false，所以我们不需要初始化
        //定义一个集合，保存所有出现的类
        HashSet<String> set = new HashSet<>();
        //遍历字符串数组
        for (String s : arr) {
            char[] str = s.toCharArray();
            for (char c : str) {
                //不管这个字符之前是否出现过，这里出现了，所以直接置为true
                occurLetter[c - 97] = true;
            }
            //将出现过的字符拼接在一起
            StringBuilder type = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (occurLetter[i]) {
                    type.append((char) (i + 97));
                }
            }
            set.add(type.toString());
            //最后要把occurLetter重置
            Arrays.fill(occurLetter, false);
        }
        //由于set添加不保留重复项，所以不用担心重复问题
        //直接返回set集合大小即可
        return set.size();
    }

    public static int types2(String[] arr) {
        HashSet<Integer> types = new HashSet<>();
        for (String s : arr) {
            char[] str = s.toCharArray();
            int key = 0;
            for (int i = 0; i < str.length; i++) {
                key |= (1 << (str[i] - 'a'));
            }
            types.add(key);
        }
        return types.size();
    }

    // for test
    public static String[] getRandomStringArray(int possibilities, int strMaxSize, int arrMaxSize) {
        String[] ans = new String[(int) (Math.random() * arrMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = getRandomString(possibilities, strMaxSize);
        }
        return ans;
    }

    // for test
    public static String getRandomString(int possibilities, int strMaxSize) {
        char[] ans = new char[(int) (Math.random() * strMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strMaxSize = 10;
        int arrMaxSize = 100;
        int testTimes = 500000;
        System.out.println("test begin, test time : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            String[] arr = getRandomStringArray(possibilities, strMaxSize, arrMaxSize);
            int ans1 = types1(arr);
            int ans2 = types2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");

    }

}
