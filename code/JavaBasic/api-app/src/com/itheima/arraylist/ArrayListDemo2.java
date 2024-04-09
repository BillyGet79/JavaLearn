package com.itheima.arraylist;

import java.util.ArrayList;

/**
 * 目标：能够使用泛型约束ArrayList集合操作的数据类型
 */
public class ArrayListDemo2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Java");
        list.add("MySQL");
        //list.add(23);
        //list.add(23.5);

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(23);
        list1.add(100);
        //list1.add("Java");
    }
}
