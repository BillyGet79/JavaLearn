package com.itheima;

import java.util.Random;
import java.util.Scanner;

/**
 * 需求：双色球模拟
 */
public class Test7 {
    public static void main(String[] args) {
        //1、随机6个红球号码(1-33，不能重复)，随机一个蓝球号码(1-16)，可以采用数组装起来作为中奖号码
        int[] luckyNumbers = createLuckyNumber();
        //printArray(luckyNumbers);

        //2、调用一个号码让用户输入7个号码，作为用户选号
        int[] userNumbers = userInputNumbers();
        //printArray(userNumbers);

        //3、输出中奖金额
        judge(luckyNumbers, userNumbers);

    }

    public static int[] createLuckyNumber(){
        //定义一个动态初始化的数组，存储7个数字
        int[] numbers = new int[7];
        //遍历数组，为每个位置生成对应的号码。
        Random r = new Random();
        for (int i = 0; i < numbers.length - 1; i++) {
            int data = r.nextInt(33) + 1;
            while (isExist(numbers, data)){
                data = r.nextInt(33) + 1;
            }
            numbers[i] = data;
        }
        numbers[numbers.length - 1] = r.nextInt(16) + 1;
        return numbers;
    }

    public static int[] userInputNumbers() {
        //定义一个数组存储7个号码
        int[] numbers = new int[7];
        //让用户录入6个红球号码
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < numbers.length - 1; i++) {
            System.out.println("请您输入第" + (i + 1) + "个红球号码：");
            int data = sc.nextInt();
            while (!isCurrect(numbers, data)){
                System.out.println("输入的号码不符合规则，请重新输入第" + (i + 1) + "个红球号码：");
                data = sc.nextInt();
            }
            numbers[i] = data;
        }
        System.out.println("请您输入蓝球号码：");
        int data = sc.nextInt();
        while (!isCurrect(data)){
            System.out.println("输入的号码不符合规则，请重新输入蓝球号码：");
            data = sc.nextInt();
        }
        numbers[numbers.length - 1] = data;
        return numbers;

    }

    public static void judge(int[] luckNumbers, int[] userNumbers){
        int redcount = 0;
        for (int i = 0; i < userNumbers.length - 1; i++) {
            if (isExist(luckNumbers, userNumbers[i])){
                redcount++;
            }
        }
        boolean blue = luckNumbers[luckNumbers.length - 1] == userNumbers[userNumbers.length - 1];
        if(blue){
            switch (redcount){
                case 6:
                    System.out.println("恭喜您获得最高1000万元奖金！");
                    break;
                case 5:
                    System.out.println("恭喜您获得3000元奖金！");
                    break;
                case 4:
                    System.out.println("恭喜您获得200元奖金！");
                    break;
                case 3:
                    System.out.println("恭喜您获得10元奖金！");
                    break;
                default:
                    System.out.println("恭喜您获得5元奖金！");
            }
        }else {
            switch (redcount){
                case 6:
                    System.out.println("恭喜您获得最高500万元奖金！");
                    break;
                case 5:
                    System.out.println("恭喜您获得200元奖金！");
                    break;
                case 4:
                    System.out.println("恭喜您获得10元奖金！");
                    break;
                default:
                    System.out.println("很遗憾，您没有获得奖金！");
                    break;
            }
        }
    }

    public static boolean isExist(int[] arr, int number){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number){
                return true;
            }
        }
        return false;
    }

    public static boolean isCurrect(int[] arr,int number){
        return number >= 1 && number <= 33 && !isExist(arr, number);
    }

    public static boolean isCurrect(int number){
        return number >= 1 && number <= 16;
    }
}
