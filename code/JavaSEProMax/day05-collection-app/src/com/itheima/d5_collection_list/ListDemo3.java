package com.itheima.d5_collection_list;

import java.util.LinkedList;

public class ListDemo3 {
    public static void main(String[] args) {
        //LinkedList可以完成队列结构，和栈结构
        //1、做一个队列
        LinkedList<String> queue = new LinkedList<>();
        //入队
        queue.offerLast("1号");
        queue.offerLast("2号");
        queue.offerLast("3号");
        System.out.println(queue);
        //出队
        //System.out.println(queue.getFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue);

        //2、做一个栈
        LinkedList<String> stack = new LinkedList<>();
        //入栈 压栈
        stack.push("第一颗子弹");
        stack.push("第二颗子弹");
        stack.push("第三颗子弹");
        stack.push("第四颗子弹");
        System.out.println(stack);

        //出栈 弹栈
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
