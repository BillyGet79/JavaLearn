package com.itheima.d5_reflect_method;

import org.junit.Test;

import java.lang.reflect.Method;

public class MethodDemo01 {
    /**
     * 1、获得类中的所有成员方法对象
     */
    @Test
    public void getDeclaredMethods() {
        //a、获取类对象
        Class c = Dog.class;
        //b、提取全部方法，包括私有的
        Method[] methods = c.getDeclaredMethods();
        //c、遍历全部方法
        for (Method method : methods) {
            System.out.println(method.getName() + "返回值类型：" +
                    method.getReturnType() + "参数个数：" + method.getParameterCount());
        }
    }

    /**
     * 2、获取某个方法对象
     */
    @Test
    public void getDeclaredMethod() throws Exception {
        //a、获取类对象
        Class c = Dog.class;
        //b、提取单个方法对象
        Method method1 = c.getDeclaredMethod("eat");
        Method method2 = c.getDeclaredMethod("eat", String.class);

        //暴力打开权限
        method1.setAccessible(true);

        //c、触发方法的执行
        Dog d = new Dog();
        //注意：方法如果是没有结果回来的，那么返回的是null
        Object result1 = method1.invoke(d);
        System.out.println(result1);

        method2.setAccessible(true);

        Object result2 = method2.invoke(d, "骨头");
        System.out.println(result2);


    }
}
