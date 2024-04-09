package com.itheima.d3_static_code;

import java.util.ArrayList;

public class StaticDemo1 {

    public static String schoolName;
    public static ArrayList<String> cards = new ArrayList<>();

    /**
     * 静态代码块，有static修饰，属于类，与类一起优先加载一次，自动触发执行
     * 作用：可以用于初始化静态资源
     */
    static {
        System.out.println("--------静态代码块被触发执行了------------");
        schoolName = "黑马";
        cards.add("3");
        cards.add("4");
    }

    public static void main(String[] args) {
        //目标：先理解静态代码块
        System.out.println("------------main方法执行------------");
        System.out.println(schoolName);
    }
}
