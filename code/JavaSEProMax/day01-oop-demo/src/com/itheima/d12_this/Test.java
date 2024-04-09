package com.itheima.d12_this;

public class Test {
    public static void main(String[] args) {
        //目标：理解this(...)的作用，本类构造器中访问本类兄弟构造器
        Student s1 = new Student("殷素素", "冰火岛自学");
        System.out.println(s1.getName());
        System.out.println(s1.getSchoolName());

        Student s2 = new Student("张三丰");
        System.out.println(s2.getName());
        System.out.println(s2.getSchoolName());
    }
}
