package com.itheima.d9_lambda;

public class LembdaDemo2 {
    public static void main(String[] args) {
        //Lambda只能简化接口中只有一个抽象方法的匿名内部类形式
        Swimming s1 = () -> {
            System.out.println("老师游得贼溜~~~");
        };
        go(s1);

        System.out.println("---------------------");
        go(() -> {
            System.out.println("学生游得很开心~~~");
        });
    }

    public static void go(Swimming s){
        System.out.println("开始。。。");
        s.swim();
        System.out.println("结束。。。");
    }
}

@FunctionalInterface    //一旦加上这个注解必须是函数式接口，里面只能有一个抽象方法
interface Swimming{
    void swim();
}
