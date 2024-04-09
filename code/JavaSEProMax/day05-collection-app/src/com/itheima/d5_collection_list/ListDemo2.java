package com.itheima.d5_collection_list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListDemo2 {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add("java1");
        lists.add("java2");
        lists.add("java3");
        System.out.println(lists);

        //1、for循环
        System.out.println("------------");
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }

        //2、迭代器
        System.out.println("-------------");
        Iterator<String> iterator = lists.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //3、foreach
        System.out.println("------------");
        for (String list : lists) {
            System.out.println(list);
        }

        //4、lambda表达式
        System.out.println("--------------");
        lists.forEach(s -> {
            System.out.println(s);
        });

    }
}
