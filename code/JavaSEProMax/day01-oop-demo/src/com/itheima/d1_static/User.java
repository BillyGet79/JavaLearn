package com.itheima.d1_static;

public class User {
    /**
     * 在线人数
     * 注意：static修饰的成员变量，静态成员变量，只在内存中有一份，可以被共享
     */
    public static int onlineNumber = 161;
    /**
     * 实例成员变量，无static修饰，属于每个对象的，必须用对象名访问
     */
    private String name;
    private int age;

    public static void main(String[] args) {
        //目标：理解static修饰成员变量的作用和访问特点
        //1、类名，静态成员变量
        System.out.println(User.onlineNumber);

        //2、实例成员变量
        //System.out.println(User.name);    报错
        User u = new User();
        u.name = "张三";
        u.age = 21;
        System.out.println(u.name);
        System.out.println(u.age);
        u.onlineNumber++;
        System.out.println(u.onlineNumber);

        //注意：同一个类中静态成员变量的访问可以省略类名
        System.out.println(onlineNumber);

    }
}
