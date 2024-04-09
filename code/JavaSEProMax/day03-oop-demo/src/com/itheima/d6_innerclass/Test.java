package com.itheima.d6_innerclass;

public class Test {
    public static void main(String[] args) {
        Outer.Inner in = new Outer().new Inner();
        in.setName("内部");
        in.show();
        Outer.Inner.test();
    }
}
