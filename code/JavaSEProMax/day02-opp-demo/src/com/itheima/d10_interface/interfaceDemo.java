package com.itheima.d10_interface;

/**
 * 声明了一个接口：体现一种规范，规范一定是公开的
 */
public interface interfaceDemo {
    //目标：接口中的成分特点，JDK8之前接口中只能有抽象方法和常量
    //1、常量
    String SCHOOL_NAME = "黑马程序员";
    //public static final String SCHOOL_NAME = "黑马程序员";

    //2、抽象方法
    //注意：由于接口体现规范思想，规范默认都是公开的，所以代码层面，public abstract可以省略不写。
    void run();
    //public abstract void run();
    void eat();
    //public abstract void eat();
}
