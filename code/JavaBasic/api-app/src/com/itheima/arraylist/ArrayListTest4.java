package com.itheima.arraylist;

import java.util.ArrayList;

public class ArrayListTest4 {
    public static void main(String[] args) {
        //目标：学习遍历并删除元素的正确方案
        //1、创建于给ArrayList集合存储一个班级学生的成绩
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(98);
        scores.add(77);
        scores.add(66);
        scores.add(89);
        scores.add(79);
        scores.add(50);
        scores.add(100);

        //2、把低于80分的成绩从集合中去掉

        //方案一：
        /*
        for (int i = 0; i < scores.size(); i++) {
            int score = scores.get(i);
            if (score < 80){
                //这个分数必须删除
                scores.remove(i);
                i--;            //删除成功后，必须退一步！
            }
        }
        System.out.println(scores);
         */

        //方案二：
        //从后面倒着遍历删除就可以
        for (int i = scores.size() - 1; i >= 0; i--) {
            int score = scores.get(i);
            if (score < 80){
                scores.remove(i);
            }
        }
        System.out.println(scores);

    }
}
