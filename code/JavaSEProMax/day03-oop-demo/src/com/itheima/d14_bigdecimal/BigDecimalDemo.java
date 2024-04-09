package com.itheima.d14_bigdecimal;

import java.math.BigDecimal;

public class BigDecimalDemo {
    public static void main(String[] args) {
        //浮点型运算的时候直接+ * / 可能会出现数据失真
        //包装浮点型数据成为大数据对象 BigDecimal
        double a = 0.1;
        double b = 0.2;
        double c = a + b;
        System.out.println(c);
        BigDecimal a1 = BigDecimal.valueOf(a);
        BigDecimal b1 = BigDecimal.valueOf(b);
        BigDecimal c1 = a1.add(b1);
        System.out.println(c1);

        //目的：double
        double rs = c1.doubleValue();
        System.out.println(rs);

        //注意事项：BigDecimal是一定要精度运算的
    }
}
