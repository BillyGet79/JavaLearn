package com.itheima.d7_abstract_test;

public class Test {
    public static void main(String[] args) {
        //目标：学习一下抽象类的基本使用：做父类，被继承，重写
        GoldCard c = new GoldCard();
        c.setMoney(10000);
        c.setUserName("dlei");

        c.pay(300);
        System.out.println("剩余：" + c.getMoney());
    }
}
