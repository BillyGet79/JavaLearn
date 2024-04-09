package com.itheima.d5_collection_list;

import java.util.ArrayList;
import java.util.List;

public class ListDemo1 {
    public static void main(String[] args) {
        //1、创建一个ArrayList集合对象
        //List：有序，可重复，有索引的
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Java");
        list.add("HTML");
        list.add("HTML");
        list.add("MySQL");
        list.add("MySQL");
        //2、在某个索引位置插入元素
        list.add(2,"黑马");
        System.out.println(list);

        //3、根据索引删除元素，返回被删除元素
        System.out.println(list.remove(1));
        System.out.println(list);

        //4、根据索引获取元素
        System.out.println(list.get(1));

        //5、修改索引位置出的元素
        System.out.println(list.set(1, "传智教育"));
        System.out.println(list);
    }
}
