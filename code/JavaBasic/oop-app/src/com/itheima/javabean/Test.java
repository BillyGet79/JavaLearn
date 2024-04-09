package com.itheima.javabean;

public class Test {
    public static void main(String[] args) {
        //1、创建对象封装数据，无参数构造器
        User u1 = new User();
        u1.setName("黑马吴彦祖");
        u1.setHeight(186);
        u1.setSex('男');
        u1.setAge(45);
        System.out.println(u1.getName());
        System.out.println(u1.getHeight());
        System.out.println(u1.getSex());
        System.out.println(u1.getAge());

        //2、创建对象封装数据，有参数构造器
        User u2 = new User("黑马关之琳", 172, '女', 48);
        System.out.println(u2.getName());
        System.out.println(u2.getHeight());
        System.out.println(u2.getSex());
        System.out.println(u2.getAge());
    }
}
