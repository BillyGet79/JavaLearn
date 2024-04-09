package com.itheima.loop;

public class WhileTest6 {
    public static void main(String[] args) {
        /*
        世界最高山峰是珠穆朗玛峰（8848860毫米），
        假如我有一张足够大的纸，它的厚度是0.1毫米。
        请问，折叠多少次，可以折成珠穆朗玛峰的高度
         */
        int count = 0;
        double peakHeight = 8848860;
        double paperThickness = 0.1;
        while (paperThickness <= peakHeight){
            paperThickness *= 2;
            count++;
        }
        System.out.println(count);
    }
}
