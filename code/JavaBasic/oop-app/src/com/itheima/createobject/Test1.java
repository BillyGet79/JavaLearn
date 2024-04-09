package com.itheima.createobject;

public class Test1 {
    public static void main(String[] args) {
        //目标：自己设计对象并使用
        //类名 对象名 = new 类名();
        Car c = new Car();
        c.name = "奔驰GLC";
        c.price = 39.78;
        System.out.println(c.name);
        System.out.println(c.price);
        c.start();
        c.run();

        Car c2 = new Car();
        c2.name = "宝马X3";
        c2.price = 38.98;
        System.out.println(c2.name);
        System.out.println(c2.price);
        c2.start();
        c2.run();
    }
}
