package class28;

public class Code02_AddShortestEnd {
    public static String shortestEnd(String s){
        if (s == null || s.isEmpty()){
            return null;
        }
        char[] str = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]).append('#');
        }
        str = sb.toString().toCharArray();
        int R = -1;
        int C = -1;
        int[] pArr = new int[str.length];
        int maxContainsEnd = -1;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if (str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R){
                R = pArr[i] + i;
                C = i;
            }
            if (R == str.length){
                maxContainsEnd = pArr[i];
                break;
            }
        }
        //这里只返回了添加的内容
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = str[i * 2 + 1];
        }
        return String.valueOf(res);
    }


    //测试
    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(str1 + shortestEnd(str1));
    }
}
