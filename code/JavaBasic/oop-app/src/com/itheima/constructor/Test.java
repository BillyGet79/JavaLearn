package com.itheima.constructor;

/**
 * 目标：说出对象是通过构造器初始化出来的，并理解构造器的分类和区别
 */
public class Test {
    public static void main(String[] args) {
        Car c = new Car();
        System.out.println(c.name);
        System.out.println(c.price);

        Car c2 = new Car("宝马X6", 80);
        System.out.println(c2.name);
        System.out.println(c2.price);
    }
}
