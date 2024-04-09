package class19;

public class Code02_CovertToLetterString {
    public static int number(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }
    public static int process(char[] chars, int index) {
        if (index == chars.length){
            return 1;
        }
        //i没到最后
        if (chars[index] == '0'){   //之前的决定有问题，返回0
            return 0;
        }
        //str[i] ！= '0'
        //可能性1，i单转
        int ways = process(chars, index + 1);
        if (index + 1 < chars.length && (chars[index] - '0') * 10 + chars[index + 1] - '0' < 27){
            ways += process(chars, index + 2);
        }
        return ways;
    }

    public static int dp(String str){
        if (str == null || str.isEmpty()){
            return 0;
        }
        char[] strs = str.toCharArray();
        int N = strs.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int index = N - 1; index >= 0; index--) {
            if (strs[index] == '0'){
                continue;
            }
            dp[index] = dp[index + 1];
            if (index + 1 < strs.length && (strs[index] - '0') * 10 + strs[index + 1] - '0' < 27){
                dp[index] += dp[index + 2];
            }
        }
        return dp[0];
    }

    //对数器
    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp(s);
            if (ans0 != ans1) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
