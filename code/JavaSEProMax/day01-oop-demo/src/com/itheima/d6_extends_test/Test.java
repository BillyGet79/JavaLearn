package com.itheima.d6_extends_test;

public class Test {
    public static void main(String[] args) {
        //目标：理解继承的设计思想
        Student s = new Student();
        s.setName("蜘蛛精");   //使用父类的
        s.setAge(999);
        System.out.println(s.getName());
        s.queryCourses();   //父类的
        s.writeInfo();  //子类的方法
    }
}
