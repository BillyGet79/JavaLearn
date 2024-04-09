package com.itheima.d9_map_impl;

import com.itheima.d1_set.Apple;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo3 {
    public static void main(String[] args) {
        Map<Integer, String> maps1 = new TreeMap<>();
        maps1.put(13 , "王麻子");
        maps1.put(1 , "张三");
        maps1.put(3 , "县长");
        System.out.println(maps1);

        //TreeMap集合自带排序
        Map<Apple, String> maps2 = new TreeMap<>((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
        maps2.put(new Apple("红富士", "红色", 9.9, 500), "山东" );
        maps2.put(new Apple("青苹果", "绿色", 15.9, 300), "广州");
        maps2.put(new Apple("绿苹果", "青色", 29.9, 400), "江西");
        maps2.put(new Apple("黄苹果", "黄色", 9.8, 500), "湖北");

        System.out.println(maps2);
    }
}
