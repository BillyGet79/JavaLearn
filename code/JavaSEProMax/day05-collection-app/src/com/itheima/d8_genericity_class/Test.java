package com.itheima.d8_genericity_class;

public class Test {
    public static void main(String[] args) {
        //需求：模拟ArrayList定义一个MyArrayList，关注泛型设计
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Java");
        list.add("Java");
        list.add("MySQL");
        list.remove("MySQL");
        System.out.println(list);

        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(23);
        list1.add(24);
        list1.add(25);
        list1.remove(25);
        System.out.println(list1);
    }
}
