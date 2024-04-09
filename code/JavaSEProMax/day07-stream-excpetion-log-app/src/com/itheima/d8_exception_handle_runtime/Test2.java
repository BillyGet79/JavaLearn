package com.itheima.d8_exception_handle_runtime;

import java.util.Scanner;

/**
 * 需求：需要输入一个合法的价格位置 要求价格大于0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("请您输入合法的价格");
            String priceStr = sc.nextLine();
            //转换成double类型的价格
            double price = 0;
            try {
                price = Double.valueOf(priceStr);
            } catch (Exception e) {
                System.out.println("用户输入的数据有毛病，请您输入合法的数值，建议为正数！");
            }

            //判断价格是否大于0
            if (price > 0){
                System.out.println("定价：" + price);
                break;
            }else {
                System.out.println("价格必须是正数~~~");
            }
        }
    }
}
