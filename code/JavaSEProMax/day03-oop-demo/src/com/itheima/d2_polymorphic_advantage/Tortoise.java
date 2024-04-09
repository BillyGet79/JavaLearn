package com.itheima.d2_polymorphic_advantage;

public class Tortoise extends Animal {
    public String name = "乌龟名称";
    @Override
    public void run(){
        System.out.println("🐢跑的非常慢~~~");
    }
}
