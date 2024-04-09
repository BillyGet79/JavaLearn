package com.itheima.d3_reflect_constructor;

import org.junit.Test;

import java.lang.reflect.Constructor;

public class TestStudent01 {
    //1、获取全部构造器：只能获取public修饰的构造器
    @Test
    public void getConstructors() {
        //a、获取类对象
        Class c = Student.class;
        //b、提取类中的全部的构造器对象（只能拿public构造器）
        Constructor[] constructors = c.getConstructors();
        //c、遍历构造器
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName() + "===>" + constructor.getParameterCount());
        }
    }

    //2、获取全部的构造器：只要你敢写，这里就能拿到，无所谓权限是否可及
    @Test
    public void getDeclaredConstructors() {
        //a、获取类对象
        Class c = Student.class;
        //b、提取类中的全部的构造器对象（所有的都能拿）
        Constructor[] constructors = c.getDeclaredConstructors();
        //c、遍历构造器
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName() + "===>" + constructor.getParameterCount());
        }
    }


    //3、获取某个构造器：只能拿public修饰的某个构造器
    @Test
    public void getConstructor() throws Exception {
        //a、获取类对象
        Class c = Student.class;
        //b、提取类中的全部的构造器对象（按照参数定位无参数构造器）
        Constructor constructor = c.getConstructor();
        System.out.println(constructor.getName() + "===>" + constructor.getParameterCount());
    }


    //4、获取某个构造器：只要你敢写，这里就能拿到，无所谓权限是否可及
    @Test
    public void getDeclaredConstructor() throws Exception {
        //a、获取类对象
        Class c = Student.class;
        //b、提取类中的全部的构造器对象（按照参数定位无参数构造器）
        Constructor constructor = c.getDeclaredConstructor();
        System.out.println(constructor.getName() + "===>" + constructor.getParameterCount());

        //c、定位某个有参数构造器
        Constructor constructor1 = c.getDeclaredConstructor(String.class, int.class);
        System.out.println(constructor1.getName() + "===>" + constructor1.getParameterCount());
    }
}
