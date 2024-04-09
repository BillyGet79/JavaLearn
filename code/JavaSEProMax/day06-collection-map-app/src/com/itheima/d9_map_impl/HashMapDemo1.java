package com.itheima.d9_map_impl;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo1 {
    public static void main(String[] args) {
         // Map集合是根据键去除重复元素
        Map<Student, String> maps = new HashMap<>();

        Student s1 = new Student("无恙", 20, '男');
        Student s2 = new Student("无恙", 20, '男');
        Student s3 = new Student("周雄", 21, '男');

        maps.put(s1, "北京");
        maps.put(s2, "上海");
        maps.put(s3, "广州");

        System.out.println(maps);
    }
}
