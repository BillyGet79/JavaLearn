package com.itheima.d2_modifier;

public class Fu {
    /**
     * 1、定义私有成员
     */
    private void privateMethod(){
        System.out.println("----private----");
    }

    /**
     * 2、定义缺省修饰的成员
     */
    void method(){
        System.out.println("---缺省---");
    }

    /**
     * 3、protected修饰的方法
     */
    protected void protectedMethod(){
        System.out.println("---protected----");
    }

    /**
     * 4、public修饰的方法
     */
    public void publicMethod(){
        System.out.println("---public---");
    }

    public static void main(String[] args) {
        Fu f = new Fu();
        f.privateMethod();
        f.method();
        f.protectedMethod();
        f.publicMethod();
    }

}
