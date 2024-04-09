package com.itheima.thisdemo;

public class Test {
    public static void main(String[] args) {
        Car c = new Car("奔驰", 45);
        System.out.println(c.name);
        System.out.println(c.price);

        System.out.println("---------------");
        c.goWith("宝马");
    }
}
