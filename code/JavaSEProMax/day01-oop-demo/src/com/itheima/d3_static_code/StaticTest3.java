package com.itheima.d3_static_code;

import java.util.ArrayList;

public class StaticTest3 {

    /**
     * 1、定义一个静态的集合，这样这个集合值加载一个。
     */
    public static ArrayList<String> cards = new ArrayList<>();

    /**
     * 2、在程序真正运行mainf方法前，把54张牌放进去，后续可以直接使用
     */
    static {
        //3、正式做牌，放到集合中去
        //a.定义要给数组存储全部点数
        String[] size = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        //b.定义一个数组存储全部的花色：类型确定了，个数确定了
        String[] colors = {"♥", "♠", "♦", "♣"};
        //c.遍历点数
        for (int i = 0; i < size.length; i++) {
            for (int j = 0; j < colors.length; j++) {
                String card = size[i] + colors[j];
                cards.add(card);
            }
        }
        //d.单独加入大小王
        cards.add("小🃏");
        cards.add("大🃏");
    }

    public static void main(String[] args) {
        //目标：模拟游戏启动前，初始化54张牌数据
        System.out.println("新牌：" + cards);
    }
}
