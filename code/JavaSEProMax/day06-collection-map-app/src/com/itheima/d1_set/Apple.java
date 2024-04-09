package com.itheima.d1_set;

public class Apple implements Comparable<Apple>{
    private String name;
    private String color;
    private int weight;
    private double price;

    public Apple() {
    }

    public Apple(String name, String color, double price, int weight) {
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }

    /**
     * 方式一：类自定义比较规则
     * o1.compareTo(o2)
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Apple o) {
        //按照重量进行比较
        return this.weight - o.weight >= 0 ? 1 : -1;
    }
}
