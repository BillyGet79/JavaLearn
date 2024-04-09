package com.itheima.branch;

public class SwitchDemo3 {
    public static void main(String[] args) {
        //目标：清楚switch的注意点。并在开发的时候注意
        //表达式类型只能是byte、short、int、char，JDK5开始支持枚举，JDK7开始支持String，不支持double、float、long
        double a = 12.3;
//        switch (a){
//
//        }     //不支持double、float、long，会报错

        //case给出的值不允许重复，且只能是字面量，不能是变量
        int a1 = 31;
//        switch (3){
//            case 31:
//                break;
//            case 31:      //重复会报错
//                break;
//            case a1:      //不可以是变量
//                break;
//        }

        //不要忘记写break，否则会出现穿透现象


    }
}
