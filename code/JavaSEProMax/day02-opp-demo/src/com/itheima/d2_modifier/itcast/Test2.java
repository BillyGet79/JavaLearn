package com.itheima.d2_modifier.itcast;

import com.itheima.d2_modifier.Fu;

public class Test2 {
    public static void main(String[] args) {
        Fu f = new Fu();
        //f.privateMethod();    //报错
        //f.method();   //报错
        //f.protectedMethod();  //报错
        f.publicMethod();
    }
}
