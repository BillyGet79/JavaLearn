package com.itheima.d3_ploymorphic_convert;

public class Dog extends Animal {
    public String name = "狗名称";
    @Override
    public void run(){
        System.out.println("🐕跑的贼快~~~");
    }

    /**
     * 独有功能
     */
    public void lookDoor(){
        System.out.println("🐕在看🚪！！！");
    }
}
