package com.itheima.d9_abstract_template;

public class Test {
    public static void main(String[] args) {
        //目标：理解模板方法模式的思想和使用步骤
        StudentMiddle s = new StudentMiddle();
        s.write();

        StudentChild s2 = new StudentChild();
        s2.write();
    }
}
