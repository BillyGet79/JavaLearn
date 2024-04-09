package Test2678;

//https://leetcode.cn/problems/number-of-senior-citizens/description/?envType=daily-question&envId=2023-10-23
public class Solution {

    public static int countSeniors(String[] details) {
        if (details == null || details.length == 0){
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < details.length; i++) {
            char sex = details[i].charAt(10);
            if (sex == 'M' || sex == 'F'){
                int p1 = details[i].charAt(11) - '0';
                int p2 = details[i].charAt(12) - '0';
                if (p1 * 10 + p2 > 60){
                    ans++;
                }
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        String[] details = new String[]{"9751302862F0693","3888560693F7262","5485983835F0649","2580974299F6042","9976672161M6561","0234451011F8013","4294552179O6482"};
        System.out.println(countSeniors(details));
    }

}
