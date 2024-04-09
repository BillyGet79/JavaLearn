package com.itheima.d1_set;

import java.util.HashSet;
import java.util.Set;

/**
 * 目标：让Set集合把重复内容的对象去掉一个
 */
public class SetDemo3 {
    public static void main(String[] args) {
        Set<Student> sets = new HashSet<>();
        Student s1 = new Student("无恙",20,'男');
        Student s2 = new Student("无恙",20,'男');
        Student s3 = new Student("周雄",21,'男');

        sets.add(s1);
        sets.add(s2);
        sets.add(s3);

        System.out.println(sets);
    }
}
