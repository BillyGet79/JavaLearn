package com.itheima.d11_extends_constructor;

public class Test {
    public static void main(String[] args) {
        //目标：学习子类构造器如何去访问父类有参数构造器，还要清楚其作用
        Teacher t = new Teacher("dlei", 18);
        System.out.println(t.getName());
        System.out.println(t.getAge());

    }
}
