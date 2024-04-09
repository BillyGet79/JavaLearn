package com.itheima.d3_reflect_constructor;

import org.junit.Test;

import java.lang.reflect.Constructor;

public class TestStudent02 {
    //1、调用无参构造器得到一个类的对象返回
    @Test
    public void getDeclaredConstructor() throws Exception {
        //a、获取类对象
        Class c = Student.class;
        //b、提取类中的全部的构造器对象（按照参数定位无参数构造器）
        Constructor constructor = c.getDeclaredConstructor();
        System.out.println(constructor.getName() + "===>" + constructor.getParameterCount());

        //如果遇到了私有的构造器，可以暴力反射
        constructor.setAccessible(true);    //权限被打开

        Student s = (Student) constructor.newInstance();
        System.out.println(s);

        //c、定位某个有参数构造器
        Constructor constructor1 = c.getDeclaredConstructor(String.class, int.class);
        System.out.println(constructor1.getName() + "===>" + constructor1.getParameterCount());

        Student s1 = (Student) constructor1.newInstance("孙悟空", 1000);
        System.out.println(s1);

    }
}
