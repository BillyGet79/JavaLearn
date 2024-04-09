package com.itheima.create;

public class ArrayAttentionDemo3 {
    public static void main(String[] args) {
        //目标：理解数组的注意事项
        //1、数据类型[] 数组名称 也可以写成 数据类型 数据名称[]
        int[] ages = {11, 23, 45};
        //int ages1[] = {11, 23, 45};

        //2、什么类型的数组只能存放什么类型的元素
        //String[] name = {"西门吹雪", "孤独求败", 23};   //错误的

        //3、数组一旦定义出来之后，类型和长度就固定了
        int[] ages2 = {11, 23, 45};
        //System.out.println(ages2[3]);   //报错，越界访问
    }
}
