package com.test;

public class Main {
    public static void main(String[] args) {
        Test01 f1 = new Test02();
        System.out.println(f1.a);
    }
}

class Test01 {
    int a = 1;
    public Test01() {
        System.out.println("Test01");
    }
}

class Test02 extends Test01 {
    int a = 2;
    public Test02() {
        System.out.println("Test02");
    }
}
