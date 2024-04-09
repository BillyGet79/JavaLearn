package com.itheima.d4_collection_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameDemo {
    /**
     * 1、定义一个静态的集合存储54张牌对象
     */
    public static List<Card> allCards = new ArrayList<>();

    /**
     * 2、做牌：静态代码块初始化牌数据
     */
    static {
        //3、定义点数：个数确定，类型确定，使用数组
        String[] sizes = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2",};
        //4、定义花色：个数确定，类型确定，使用数组
        String[] colors = {"♠", "♥", "♣", "♦"};
        //5、组合点数和花色
        int index = 0; //记录牌的大小
        for (String size : sizes) {
            index++;
            for (String color : colors) {
                //6、封装成一个牌对象
                Card c = new Card(size, color, index);
                //7、存入到集合容器中去
                allCards.add(c);
            }
        }
        //8、大小王存入到集合对象中去
        Card c1 = new Card("小", "🃏", ++index);
        Card c2 = new Card("大", "🃏", ++index);
        Collections.addAll(allCards, c1, c2);
        System.out.println("新牌：" + allCards);
    }

    public static void main(String[] args) {
        //9、洗牌
        Collections.shuffle(allCards);
        System.out.println("洗牌后：" + allCards);

        //10、发牌（定义三个玩家，每个玩家的牌也是一个集合容器）
        List<Card> linghuchong = new ArrayList<>();
        List<Card> jiumozhi = new ArrayList<>();
        List<Card> renyingying = new ArrayList<>();

        //11、开始发牌
        for (int i = 0; i < allCards.size() - 3; i++) {
            //先拿到当前牌对象
            Card c = allCards.get(i);
            int temp = i % 3;
            switch (temp){
                case 0:
                    linghuchong.add(c);
                    break;
                case 1:
                    jiumozhi.add(c);
                    break;
                case 2:
                    renyingying.add(c);
                    break;
                default:
                    break;
            }
        }

        //12、拿到最后三张底牌（把最后三张牌截取成一个子集合）
        List<Card> lastThreeCards = allCards.subList(allCards.size() - 3, allCards.size());

        //13、给玩家的牌排序（从大到小）
        sortCard(linghuchong);
        sortCard(jiumozhi);
        sortCard(renyingying);

        //14、输出玩家的牌
        System.out.println("啊冲" + linghuchong);
        System.out.println("啊揪" + jiumozhi);
        System.out.println("盈盈" + renyingying);
        System.out.println("底牌" + lastThreeCards);


    }

    public static void sortCard(List<Card> cards){
        Collections.sort(cards, (c1, c2) -> c2.getIndex() - c1.getIndex());
    }

}
