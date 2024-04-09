package com.itheima.d3_factory_pattern;

public class FactoryPattern {
    /**
     * 定义一个方法，创建对象返回
     */
    public static Computer createComputer(String info) {
        switch (info) {
            case "huawei":
                Computer c1 = new Huawei();
                c1.setName("Huawei Matebook 14s");
                c1.setPrice(8699);
                return c1;
            case "mac":
                Computer c2 = new Mac();
                c2.setName("Macbook M2 Pro");
                c2.setPrice(16999);
                return c2;
            default:
                return null;
        }
    }
}
