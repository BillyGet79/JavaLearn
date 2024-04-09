package Test1147;

import java.util.Scanner;

/**
 * 1147.段式回文
 *      你会得到一个字符串text。你应该把它分成 k个子字符串(subtext1, subtext2，…， subtextk)，要求满足:
 *          subtexti是 非空字符串
 *          所有子字符串的连接等于 text ( 即subtext1+ subtext2+ ... + subtextk== text)
 *          对于所有 i的有效值( 即1 <= i<= k ) ，subtexti== subtextk - i + 1 均成立
 *      返回k可能最大值。
 */
public class Main {
    //测试
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String test = scanner.next();
        Solution solution = new Solution();
        System.out.println(solution.longestDecomposition(test));
    }
}

class Solution {
    public int longestDecomposition(String text) {
        int x=0;    //左指针
        int y=text.length()-1;  //右指针
        int testlength=1; //记录当前匹配长度
        int k=0;//记录结果
        while(x<=y){
            //如果相等，则此时为单独一个分组，+1后跳出循环
            if(x==y){
                k++;
                break;
            }
            //记录截取长度
            String left = text.substring(x,x+testlength);
            String right = text.substring(y,y+testlength);
            if(left.equals(right)){     //如果相等，则重置testlength，并且重新记录x与y，k+2
                x+=testlength;
                y--;
                k+=2;
                testlength=1;
            }else{      //如果不等，则testlength+1，y-1
                testlength++;
                y--;
            }
        }
        return k;
    }
}
