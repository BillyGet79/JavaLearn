package com.itheima.d3_factory_pattern;

public class FactoryDeom {
    public static void main(String[] args) {
        Computer c1 = FactoryPattern.createComputer("huawei");
        c1.start();

        Computer c2 = FactoryPattern.createComputer("mac");
        c2.start();
    }
}
