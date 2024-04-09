package com.itheima.d4_reflect_field;

import org.junit.Test;

import java.lang.reflect.Field;

public class FieldDemo01 {
    /**
     * 1、获取全部的成员变量
     * 获得所有的成员变量对应的Field对象，只要申明了就可以得到
     */
    @Test
    public void getDeclaredFields() {
        //a、定位Class对象
        Class c = Student.class;
        //b、定位全部成员变量
        Field[] fields = c.getDeclaredFields();
        //c、遍历一下
        for (Field field : fields) {
            System.out.println(field.getName() + "==>" + field.getType());
        }
    }

    @Test
    public void getDeclaredField() throws Exception {
        //a、定位Class对象
        Class c = Student.class;
        //b、根据名称定位某一个成员变量
        Field field = c.getDeclaredField("age");
        //c、遍历一下
        System.out.println(field.getName() + "==>" + field.getType());
    }
}
