package com.itheima.d1_polymorphic;

public class Dog extends Animal{
    public String name = "狗名称";
    @Override
    public void run(){
        System.out.println("🐕跑的贼溜~~~");
    }
}
