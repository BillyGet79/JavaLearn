package com.itheima.d3_collections;

import com.itheima.d1_set.Apple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsDemo2 {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("红富士","红色",9.9,500));
        apples.add(new Apple("青苹果","绿色",15.9,300));
        apples.add(new Apple("绿苹果","青色",29.9,400));
        apples.add(new Apple("黄苹果","黄色",9.8,500));

        Collections.sort(apples);
        System.out.println(apples);

        //方式二：(重写Comparator比较器)
        Collections.sort(apples, Comparator.comparingDouble(Apple::getPrice));
        System.out.println(apples);
    }
}
