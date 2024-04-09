package com.itheima.d9_api_object;

/**
 * 目标：掌握Object类中equals方法的使用
 */
public class Test2 {
    public static void main(String[] args) {
        Student s1 = new Student("周雄",'男',19);
        Student s2 = new Student("周雄",'男',19);
        //equals默认是比较两个对象的地址是否相同
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);
    }
}
