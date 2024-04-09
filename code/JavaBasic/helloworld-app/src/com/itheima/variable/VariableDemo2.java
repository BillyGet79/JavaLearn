package com.itheima.variable;

public class VariableDemo2 {
    public static void main(String[] args) {
        //目标：需要同学们理解变量使用的注意事项
        //1、变量需先声明再使用
        int a = 23;
        System.out.println(a);
        //2、变量声明后，不能存储其他类型的数据
        //a = 1.5;
        //3、变量的有效范围从定义开始到"}"截止，且在同一个范围内不能定义两个同名的变量
        {
            int b = 25;
            System.out.println(b);
            //int b = 100;    //报错
        }
        //System.out.println(b);
        System.out.println(a);
        //int a = 234;    //报错
        a = 200;    //这里不是定义，在赋值

        //4、变量定义的时候可以没有初始值，但是使用的时候必须给初始值
        int c;
        c = 12;
        System.out.println(c);
        System.out.println("------------变量在计算机底层的原理----------------");

        char ch = 'a';
        System.out.println(ch + 1);
        System.out.println(ch);

        int i1 = 0B01100001;
        System.out.println(i1);

        int i2 = 0141;
        System.out.println(i2);

        int i3 = 0x61;
        System.out.println(i3);
    }
}
