package com.itheima.d2_recursion;

public class RecursionDemo06 {

    public static int totalNumber;  //总数量

    public static int lastBottleNumber; //记录每次剩余的瓶子个数

    public static int lastCoverNumber;  //记录每次剩余的盖子个数

    public static void main(String[] args) {
        ans(10);
        System.out.println(totalNumber + "瓶啤酒" + lastCoverNumber + "个瓶盖" + lastBottleNumber + "个空瓶");
    }

    /**
     * 计算最后可以喝多少瓶啤酒，以及最后剩余多少空瓶和盖子
     * @param money
     */
    public static void ans(int money) {
        int buyNumber = money / 2;
        totalNumber += buyNumber;

        //把盖子和瓶子换算成钱
        int coverNumber = lastCoverNumber + buyNumber;
        int bottleNumber = lastBottleNumber + buyNumber;

        //统计可以换算的钱
        int allMoney = 0;
        if (coverNumber >= 4) {
            allMoney += (coverNumber / 4) * 2;
        }
        lastCoverNumber = coverNumber % 4;

        if (bottleNumber >= 2) {
            allMoney += (bottleNumber / 2) * 2;
        }
        lastBottleNumber = bottleNumber % 2;

        if (allMoney >= 2) {
            ans(allMoney);
        }
    }
}
