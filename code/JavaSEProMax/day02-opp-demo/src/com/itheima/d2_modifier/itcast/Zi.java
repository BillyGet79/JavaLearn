package com.itheima.d2_modifier.itcast;

import com.itheima.d2_modifier.Fu;

public class Zi extends Fu {
    public static void main(String[] args) {
        Zi zi = new Zi();
        //zi.privateMethod();    //报错
        //zi.method();  //报错
        zi.protectedMethod();
        zi.publicMethod();
    }
}
