package com.itheima.d3_static_code;

public class StaticDemo2 {

    private String name;

    public StaticDemo2(){
        System.out.println("==无参构造器被触发执行==");
    }

    /**
     * 实例代码块：无static修饰，属于对象，每次构建对象时，都会触发一次执行
     * 初始化实力资源
     */
    {
        name = "张三";
        System.out.println("==实例代码块被触发执行==");
    }

    public static void main(String[] args) {
        //目标：理解实例代码块（构造代码块）
        StaticDemo2 s1 = new StaticDemo2();
        System.out.println(s1.name);

        StaticDemo2 s2 = new StaticDemo2();
        System.out.println(s2.name);
    }
}
