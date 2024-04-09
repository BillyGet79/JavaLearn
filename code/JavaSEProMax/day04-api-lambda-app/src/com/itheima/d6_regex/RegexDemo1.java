package com.itheima.d6_regex;

public class RegexDemo1 {
    public static void main(String[] args) {
        //需求：校验qq号码，必须全部数字 6 - 20 位
        System.out.println(checkQQ2("251425998"));
        System.out.println(checkQQ2("2514259a98"));
        System.out.println(checkQQ2(null));
        System.out.println(checkQQ2("2344"));

    }

    public static boolean checkQQ(String qq){
        //1、判断QQ号码的长度是否满足要求
        if (qq == null || qq.length() < 6 || qq.length() > 20){
            return false;
        }

        //2、判断qq中是否全部是数字，不是返回false
        for (int i = 0; i < qq.length(); i++) {
            //获取每位字符
            char ch = qq.charAt(i);
            //判断这个字符是否不是数字
            if (ch < '0' || ch > '9'){
                return false;
            }
        }

        return true;
    }

    public static boolean checkQQ2(String qq){
        return qq != null && qq.matches("\\d{6,20}");
    }
}
