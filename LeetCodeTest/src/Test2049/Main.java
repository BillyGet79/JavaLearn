package Test2049;

import java.util.Scanner;

/**
 * 2049.统计共同度过的日子数
 *      Alice 和 Bob 计划分别去罗马开会。
 *      给你四个字符串arriveAlice，leaveAlice，arriveBob和leaveBob。Alice 会在日期arriveAlice到leaveAlice之间在城市里（日期为闭区间），
 *      而 Bob 在日期arriveBob到leaveBob之间在城市里（日期为闭区间）。
 *      每个字符串都包含 5 个字符，格式为"MM-DD"，对应着一个日期的月和日。
 *      请你返回 Alice和 Bob 同时在罗马的天数。
 *      你可以假设所有日期都在 同一个自然年，而且 不是闰年。每个月份的天数分别为：[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]。
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Scanner scanner = new Scanner(System.in);
        String arriveAlice = scanner.next();
        String leaveAlice = scanner.next();
        String arriveBob = scanner.next();
        String leaveBob = scanner.next();
        System.out.println(solution.countDaysTogether(arriveAlice, leaveAlice, arriveBob, leaveBob));
    }
}

class Solution {
    private final int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        //算出是一年中的第几天
        int[] dateAlice = during(arriveAlice, leaveAlice);
        int[] dateBob = during(arriveBob, leaveBob);
        if(dateAlice[1] > dateBob[1]){
            if(dateAlice[0] > dateBob[1]){
                return 0;
            }else if(dateAlice[0] > dateBob[0]){
                return dateBob[1] - dateAlice[0] + 1;
            }else {
                return  dateBob[1] - dateBob[0];
            }
        }else{
            if(dateBob[0] > dateAlice[1]){
                return 0;
            }
            else if(dateBob[0] > dateAlice[0]){
                return dateAlice[1] - dateBob[0] + 1;
            }else {
                return dateAlice[1] - dateAlice[0];
            }
        }
    }
    private int month(String date){
        //日期格式固定为"MM-DD"
        //获取月份
        String months = date.split("-")[0];
        return months.charAt(0) == '0' ? months.charAt(1) - '0' : months.charAt(1) - '0' + 10;
    }
    private int day(String date){
        String days = date.split("-")[1];
        int shi = days.charAt(0) - '0';
        return shi * 10 + days.charAt(1) - '0';
    }
    private int[] during(String arriveDate, String leaveDate){
        //[0]代表到达日期，[1]代表离开日期
        int[] arr = new int[2];
        for(int i = 0; i < month(arriveDate) - 1; i++){
            arr[0] += days[i];
        }
        arr[0] += day(arriveDate);
        for(int i = 0; i < month(leaveDate) - 1; i++){
            arr[1] += days[i];
        }
        arr[1] += day(leaveDate);
        return arr;
    }
}
