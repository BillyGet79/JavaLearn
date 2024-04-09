package class28;

public class Code01_Manacher {

    public static int manacher(String s) {
        if (s == null || s.isEmpty()){
            return 0;
        }
        char[] str = s.toCharArray();
        //先进行字符串元素填充
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]).append('#');
        }
        str = sb.toString().toCharArray();
        int R = -1;     //最右回文边界
        int C = -1;     //最右回文边界对应的中心点
        //定义回文半径
        int[] pArr = new int[str.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            //现在的R表示的是比对失败的位置，即当前遍历到的元素的最长的回文子串的右侧比对失败的元素位置
            //如果i在R外，那么i当前的回文半径至少为1
            //如果i在R内，那么i当前的回文半径看对称点的值与最右回文边界与其的差值哪个小，小的那一个就是当前节点的回文半径长度
            //这个语句要仔细思考
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            //如果上面的回文半径是通过i在R内通过的，那么进入这个循环之后第一个if条件判断就会直接跳出
            //如果i在R外，才会进入到这里阔边界的方案
            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if (str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                } else {
                    break;
                }
            }
            //最后更新最右回文边界以及其中心点
            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static char[] manacherString(String s){
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append('#');
        }
        return sb.toString().toCharArray();
    }



    //对数器
    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

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
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
