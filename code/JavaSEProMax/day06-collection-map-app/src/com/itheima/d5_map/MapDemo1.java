package com.itheima.d5_map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 目标：认识Map体系的特点：按照键无序、不重复、无索引。值不做要求
 */
public class MapDemo1 {
    public static void main(String[] args) {
        //1、创建一个Map集合对象
        //Map<String, Integer> maps = new HashMap<>();
        Map<String, Integer> maps = new LinkedHashMap<>();
        maps.put("鸿星尔克", 3);
        maps.put("枸杞", 100);
        maps.put("Java", 1);
        maps.put("Java", 100);  //覆盖前面的数据
        maps.put(null, null);
        System.out.println(maps);
    }

}
