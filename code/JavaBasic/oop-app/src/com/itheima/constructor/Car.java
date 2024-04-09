package com.itheima.constructor;

public class Car {
    String name;
    double price;

    //无参数构造器
    public Car(){
        System.out.println("无参数构造器被触发执行~~~");
    }

    //有参数构造器
    public Car(String n, double p){
        name = n;
        price = p;
    }

}
