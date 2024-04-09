package com.itheima.d1_static;

public class Student {
    /**
     * 实例成员变量
     */
    private String name;

    /**
     * 静态成员方法：有static修饰，归属于类
     */
    public static  int getMax(int age1, int age2){
        return age1 > age2 ? age1 : age2;
    }

    /**
     * 实例方法：属于对象的
     */
    public void study(){
        System.out.println(name + "在好好学习，天天向上");
    }

    public static void main(String[] args) {
        //1、类名，静态成员方法
        System.out.println(Student.getMax(10, 3));
        //注意：同一个类中，访问静态方法，类名可以省略不写
        System.out.println(getMax(10, 3));

        //study();    //报错
        //2、对象.实例方法
        Student s = new Student();
        s.name = "猪八戒";
        s.study();

        //3、对象.静态方法（语法是可行，但是不推荐）
        System.out.println(s.getMax(13, 34));
    }
}
