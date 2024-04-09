package com.itheima.d4_static_singleinstance;

public class Test1 {
    public static void main(String[] args) {
        //目标：理解饿汉单例的设计步骤
        SingleInstance s1 = SingleInstance.instance;
        SingleInstance s2 = SingleInstance.instance;
        System.out.println(s1 == s2);
    }
}
