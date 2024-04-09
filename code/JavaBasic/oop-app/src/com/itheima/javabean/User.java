package com.itheima.javabean;

public class User {
    //1、成员变量需要私有化
    private String name;
    private double height;
    private char sex;
    private int age;

    //3、无参数构造器是默认的
    public User(){
    }

    public User(String name, double height, char sex, int age) {
        this.name = name;
        this.height = height;
        this.sex = sex;
        this.age = age;
    }


    //2、需要提供setter getter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
