package com.itheima.memory;

public class Test {
    public static void main(String[] args) {
        //目标：理解两个变量指向同一对象
        Student s1 = new Student();
        s1.name = "小明";
        s1.sex = '男';
        s1.hobby = "睡觉、游戏、上课";
        s1.study();

        //把s1变量赋值给学生类型的变量s2
        Student s2 = s1;
        System.out.println(s2);
        System.out.println(s1);

        s2.hobby = "爱提问";
        System.out.println(s2.name);
        System.out.println(s1.hobby);
        System.out.println(s2.sex);
        s2.study();

    }
}
