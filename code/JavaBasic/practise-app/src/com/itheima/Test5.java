package com.itheima;

import java.util.Scanner;

/**
 * 需求：
 *      在唱歌比赛中，有6名评委给选手打分，分数范围是[0-100]之间的整数。
 *      选手的最后得分为：去掉最高分、最低分后的4个评委的平均分，请完成上述过程并计算出选手的得分
 */
public class Test5 {
    public static void main(String[] args) {
        //1、定义一个动态初始化的数组，用于后期录入6个评委的分数
        int[] scores = new int[6];
        //2、录入6个评委的分数
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < scores.length; i++) {
            System.out.println("请您输入第" + (i + 1) + "个评委的打分：");
            scores[i] = sc.nextInt();
        }
        //3、输出平均分
        System.out.println("最终得分是：" + finalScore(scores));
    }

    public static double finalScore(int[] arr){
        int maxVal = arr[0];
        int minVal = arr[0];
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
            maxVal = arr[i] > maxVal ? arr[i] : maxVal;
            minVal = arr[i] < minVal ? arr[i] : minVal;
        }
        sum -= maxVal;
        sum -= minVal;
        return sum * 1.0 / (arr.length - 2);
    }
}
