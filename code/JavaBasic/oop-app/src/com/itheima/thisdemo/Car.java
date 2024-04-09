package com.itheima.thisdemo;

public class Car {
    String name;
    double price;

    public Car(String name, double price){
        this.name = name;
        this.price = price;
    }

    public void goWith(String name){
        System.out.println(this.name + "正在和" + name + "比赛！");
    }
}
