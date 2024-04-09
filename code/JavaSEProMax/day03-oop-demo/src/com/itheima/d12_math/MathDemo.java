package com.itheima.d12_math;

public class MathDemo {
    public static void main(String[] args) {
        //1、取绝对值：返回正数
        System.out.println(Math.abs(10));
        System.out.println(Math.abs(-10.3));

        //2、向上取整：5
        System.out.println(Math.ceil(4.00000001));
        System.out.println(Math.ceil(4.0000000));
        //3、向下取整：4
        System.out.println(Math.floor(4.9999999999));

        //4、求指数次方
        System.out.println(Math.pow(2, 3));
        //5、四舍五入 10
        System.out.println(Math.round(4.499999));
        System.out.println(Math.round(4.5000001));

        System.out.println(Math.random());

        //拓展：3-9之间的随机数
        System.out.println((int)(Math.random() * 7) + 3);

    }
}
