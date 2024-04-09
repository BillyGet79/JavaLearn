package com.itheima.operator;

public class OperatorTest2 {
    public static void main(String[] args) {
        //需求，拆分3位数，把个位、十位、百位分别输出
        int date = 589;

        //1、个位
        int ge = date % 10;
        System.out.println(ge);

        //2、十位
        int shi = date / 10 % 10;
        System.out.println(shi);

        //3、百位
        int bai = date / 100;
        System.out.println(bai);
    }
}
