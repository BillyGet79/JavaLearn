package com.itheima.d6_collection_update_delete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 目标：研究集合兵力并删除元素可能出现的：并发修改异常问题
 */
public class Test {
    public static void main(String[] args) {
        //1、准备数据
        List<String> list = new ArrayList<>();
        list.add("黑马");
        list.add("Java");
        list.add("Java");
        list.add("赵敏");
        list.add("赵敏");
        list.add("索索");
        System.out.println(list);

        //需求：删除全部的Java信息
        //a、迭代器遍历删除
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String ele = it.next();
            if ("Java".equals(ele)){
                //删除Java
                it.remove();    //删除迭代器所在位置的元素值
            }
        }
        System.out.println(list);

        //b、foreach遍历删除（会出现问题，这种无法解决的，foreach不能边遍历边删除）
        /*for (String s : list) {
            if ("Java".equals(s)){
                list.remove(s);
            }
        }*/

        //c、lambda表达式（会出现问题）
        /*list.forEach(s -> {
            if ("Java".equals(s)){
                list.remove(s);
            }
        });*/

        //d、for循环
        for (int i = 0; i < list.size(); i++) {
            String ele = list.get(i);
            if ("Java".equals(ele)){
                list.remove(ele);
                i--;
            }
        }
        System.out.println(list);
    }
}
