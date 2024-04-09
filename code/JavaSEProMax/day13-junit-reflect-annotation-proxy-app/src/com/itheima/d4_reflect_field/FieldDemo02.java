package com.itheima.d4_reflect_field;

import org.junit.Test;

import java.lang.reflect.Field;

public class FieldDemo02 {
    @Test
    public void getDeclaredField() throws Exception {
        //a、定位Class对象
        Class c = Student.class;
        //b、根据名称定位某一个成员变量
        Field ageF = c.getDeclaredField("age");

        ageF.setAccessible(true);   //暴力打开权限

        //c、赋值
        Student s = new Student();
        ageF.set(s, 18);
        System.out.println(s);

        //d、取值
        int age = (int) ageF.get(s);
        System.out.println(age);

    }
}
