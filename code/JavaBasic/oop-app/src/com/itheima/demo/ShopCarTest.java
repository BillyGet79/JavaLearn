package com.itheima.demo;

import java.util.Scanner;

/**
 * 目标：面向对象编程训练，购物车模块模拟
 */
public class ShopCarTest {
    public static void main(String[] args) {
        //1、定义商品类，用于后期创建商品对象
        //2、定义购物车对象，使用一个数组对象表示
        Goods[] shopCar = new Goods[100];
        //3、搭建操作架构
        OUT:
        while (true) {
            System.out.println("请您选择如下命令进行操作");
            System.out.println("添加上频道购物车：add");
            System.out.println("查询商品到购物车：query");
            System.out.println("修改商品购买数量：update");
            System.out.println("结算购买商品的金额：pay");
            System.out.println("退出程序：quit");
            Scanner sc = new Scanner(System.in);
            System.out.println("请您输入命令：");
            String command = sc.next();
            switch (command){
                case "add":
                    //添加商品到购物车
                    addGoods(shopCar, sc);
                    break;
                case "query":
                    //查询购物车商品展示
                    queryGoods(shopCar);
                    break;
                case "update":
                    //修改商品的购买数量
                    updateGoods(shopCar, sc);
                    break;
                case "pay":
                    //结算金额
                    payGoods(shopCar);
                    break;
                case "quit":
                    //退出
                    break OUT;
                default:
                    System.out.println("没有该功能！");
            }
        }
    }

    /**
     * 结算金额
     * @param shopCar
     */
    public static void payGoods(Goods[] shopCar) {
        queryGoods(shopCar);
        //1、定义一个求和变量累加金额
        double money = 0;
        //2、遍历购物车数组中的全部商品对象，累加单价*数量
        for (int i = 0; i < shopCar.length; i++) {
            Goods g = shopCar[i];
            if (g != null){
                money += (g.getPrice() * g.getBuyNumber());
            }else {
                break;
            }
        }
        System.out.println("订单总金额：" + money);
    }

    /**
     * 修改商品信息
     * @param shopCar
     * @param sc
     */
    public static void updateGoods(Goods[] shopCar, Scanner sc) {
        //让用户输入要修改商品的id，根据id查询出要修改的商品对象
        while (true) {
            System.out.println("请您输入要修改的商品id：");
            int id = sc.nextInt();
            Goods g = getGoodsById(shopCar, id);
            if (g == null){
                System.out.println("对不起，没有购买商品！");
            }else {
                System.out.println("请您输入：" + g.getName() + "商品最新购买数量：");
                int buyNumber = sc.nextInt();
                g.setBuyNumber(buyNumber);
                System.out.println("修改完成！");
                queryGoods(shopCar);
                break;
            }
        }
    }

    /**
     * 查询购物车中的商品对象信息，并展示出来
     * @param shopCar
     */
    public static void queryGoods(Goods[] shopCar) {
        System.out.println("==========查询购物车信息如下=============");
        System.out.println("编号\t\t名称\t\t\t价格\t\t\t购买数量");
        for (int i = 0; i < shopCar.length; i++) {
            if (shopCar[i] != null){
                Goods g = shopCar[i];
                System.out.println(g.getId() + "\t\t" + g.getName() + "\t\t\t" + g.getPrice() + "\t\t\t" + g.getBuyNumber());
            }
            else {
                break;
            }
        }
    }

    /**
     * 完成商品添加到购物车的方法
     */
    public static void addGoods(Goods[] shopCar, Scanner sc) {
        //1、录入用户输入的购买商品的信息
        System.out.println("请您输入购买商品的编号（不重复）：");
        int id = sc.nextInt();
        System.out.println("请您输入购买商品的名称：");
        String name = sc.next();
        System.out.println("请您输入购买商品的数量：");
        int buyNumber = sc.nextInt();
        System.out.println("请您输入购买商品的价格");
        double price = sc.nextDouble();

        //2、把这些购买商品的细腻些封装成一个商品对象
        Goods g = new Goods();
        g.setId(id);
        g.setName(name);
        g.setBuyNumber(buyNumber);
        g.setPrice(price);

        //3、把这个商品对象添加到购物车数组中去
        for (int i = 0; i < shopCar.length; i++) {
            if (shopCar[i] == null){
                shopCar[i] = g;
                break;
            }
        }
        System.out.println("您的商品：" + g.getName() + "添加到 购物车完成！");
    }

    public static Goods getGoodsById(Goods[] shopCar, int id){
        for (int i = 0; i < shopCar.length; i++) {
            Goods g = shopCar[i];
            if (g != null){
                //判断这个商品对象的id是否是我们想要的
                if (g.getId() == id){
                    return g;
                }
            }else {
                return null;
            }
        }
        return null;
    }
}
