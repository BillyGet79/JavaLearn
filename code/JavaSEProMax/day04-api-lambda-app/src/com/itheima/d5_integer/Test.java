package com.itheima.d5_integer;

/**
 * 目标：明白包装类的概念，并使用
 */
public class Test {
    public static void main(String[] args) {
        int a = 10;
        Integer a1 = 11;
        Integer a2 = a;
        System.out.println(a);
        System.out.println(a1);

        Integer it = 100;
        int it1 = it;
        System.out.println(it1);

        double db = 99.5;
        Double db2 = db;
        double db3 = db2;
        System.out.println(db3);

        //int age = null;   //报错
        Integer age1 = null;
        Integer age2 = 0;

        System.out.println("----------------");
        //1、包装类可以把基本类型的数据转换成字符串形式
        Integer i3 = 23;
        String rs = i3.toString();
        System.out.println(rs + 1);

        String rs1 = Integer.toString(i3);
        System.out.println(rs1 + 1);
        //可以直接+字符串得到字符串类型
        String rs2 = i3 + "";
        System.out.println(rs2 + 1);

        System.out.println("----------------");
        String number = "23";
        //转换成整数
        //int age = Integer.parseInt(number);
        int age = Integer.valueOf(number);
        System.out.println(age + 1);

        String number1 = "99.9";
        //转换成小数
        //double score = Double.parseDouble(number1);
        double score = Double.valueOf(number1);
        System.out.println(score + 1);

    }
}
