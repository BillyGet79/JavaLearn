package com.itheima.d10_extends_constructor;

public class Dog extends Animal{
    public Dog(){
        super();    //默认存在，写不写都行
        System.out.println("子类Dog无参构造器被执行~~");
    }

    public Dog(String name){
        System.out.println("子类Dog有参构造器被执行");
    }
}
