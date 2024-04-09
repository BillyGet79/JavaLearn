package Test2520;

public class Solution {
    public int countDigits(int num){
        int t = num;
        int count = 0;
        while (t != 0){
            if (num % (t % 10) == 0){
                count++;
            }
            t /= 10;
        }
        return count;
    }
}
