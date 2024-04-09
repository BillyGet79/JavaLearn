package com.itheima.d9_lambda;

import java.util.Arrays;
import java.util.Comparator;


public class LambdaDemo3 {
    public static void main(String[] args) {
        Integer[] ages1 = {34, 12, 42, 23};
        Arrays.sort(ages1, (o1, o2) -> {
                //指定比较规则
                //return o1 - o2;//默认升序
                return o2 - o1; //降序
            }
        );
        System.out.println(Arrays.toString(ages1));
    }
}
