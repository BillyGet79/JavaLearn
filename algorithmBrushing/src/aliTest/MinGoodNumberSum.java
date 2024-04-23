package aliTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MinGoodNumberSum {

    public static int getMinGoodNumberSum(int n) {
        //首先先获得小于这个数的所有好数
        int[] goodNumbers = getGoodNumbers(n);
        //由于1一定是一个好数，所以数组当中至少有一个1
        int count = 0;
        //我们从后往前遍历这个数组
        //这里可以贪心
        int number = n;
        int ans = 0;
        for (int i = goodNumbers.length - 1; i >= 0; i--) {
            if (number >= goodNumbers[i]) {
                number -= goodNumbers[i];
                ans++;
                i++;
            }
        }
        return ans;
    }

    public static int[] getGoodNumbers(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (isGoodNumber(i)){
                list.add(i);
            }
        }
        int[] ans = new int[list.size()];
        int i = 0;
        for (Integer integer : list) {
            ans[i] = integer;
            i++;
        }
        return ans;
    }

    public static boolean isGoodNumber(int i) {
        return i % digitSum(i) == 0;
    }

    public static int digitSum(int i) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i = i / 10;
        }
        return sum;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        //用一个数组记录最后所得到的结果
        List<Integer> ans = new ArrayList<>();
        //获得输入个数
        in.nextToken();
        int m = (int) in.nval;
        for (int i = 0; i < m; i++) {
            //获得数
            in.nextToken();
            int n = (int) in.nval;
            ans.add(getMinGoodNumberSum(n));
        }
        ans.forEach(out::println);
        out.flush();
    }
}
