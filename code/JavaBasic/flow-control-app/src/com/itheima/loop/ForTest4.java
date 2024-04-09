package com.itheima.loop;

public class ForTest4 {
    public static void main(String[] args) {
        //需求：输出水仙花数
        for (int i = 100; i < 1000; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 100;
            if (ge * ge * ge + shi * shi * shi + bai * bai * bai == i){
                System.out.println(i);
            }
        }
    }
}
