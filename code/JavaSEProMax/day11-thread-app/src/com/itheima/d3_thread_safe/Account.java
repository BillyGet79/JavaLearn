package com.itheima.d3_thread_safe;

public class Account {
    private String cardId;
    private double money;   //账户余额

    public Account() {
    }

    public Account(String cardId, double money) {
        this.cardId = cardId;
        this.money = money;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * 取钱方法
     * @param money
     */
    public void drawMoney(double money) {
        //0、先获取是谁来取钱，线程的名字就是人名
        String name = Thread.currentThread().getName();
        //1、判断账户是否够钱
        if (this.money >= money) {
            //2、取钱
            System.out.println(name + "来取钱成功，吐出：" + money);
            //3、更新余额
            this.money -= money;
            System.out.println(name + "取钱后剩余：" + this.money);
        } else {
            //4、余额不足
            System.out.println(name + "来取钱，余额不足！");
        }
    }
}
