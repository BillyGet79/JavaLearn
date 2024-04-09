package class27;

public class Code01_KMP {

    public static int getIndexOf(String s1, String s2){
        if (s1 == null || s2 == null || s2.isEmpty() || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0;
        int y = 0;
        int[] next = getNextArray(str2);
        while (x < str1.length && y < str2.length){
            if (str1[x] == str2[y]){
                x++;
                y++;
            }else if (next[y] == -1){
                x++;
            }else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }
    private static int[] getNextArray(char[] str2) {
        if (str2.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0; //与遍历到的目标字符前一个位置的字符比的位置
        while (i < next.length){
            if (str2[i - 1] == str2[cn]){
                next[i++] = ++cn;
            } else if (cn > 0){
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
    //对数器
    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }
    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
