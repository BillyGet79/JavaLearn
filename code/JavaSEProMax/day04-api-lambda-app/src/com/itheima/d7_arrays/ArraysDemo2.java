package com.itheima.d7_arrays;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysDemo2 {
    public static void main(String[] args) {
        //目标：自定义数组的排序规则：Comparator比较器对象
        //1、Arrays的sort方法对于默认有值特性的数组是默认升序排序
        int[] ages = {34, 12, 42, 23};
        Arrays.sort(ages);
        System.out.println(Arrays.toString(ages));

        //2、需求：降序排序
        Integer[] ages1 = {34, 12, 42, 23};
        /**
         * 参数一：被排序的数组 必须是引用类型的元素
         * 参数二：匿名内部类对象，代表了一个比较器对象
         */
        Arrays.sort(ages1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //指定比较规则
                //return o1 - o2;//默认升序
                return o2 - o1; //降序
            }
        });
        System.out.println(Arrays.toString(ages1));

        System.out.println("-------------------------");
        Student[] students = new Student[3];
        students[0] = new Student("吴磊", 23, 175.5);
        students[1] = new Student("谢鑫", 18, 185.5);
        students[2] = new Student("王亮", 28, 195.5);

        System.out.println(Arrays.toString(students));

        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //自己指定比较规则
                return o1.getAge() - o2.getAge();   //按照年龄升序排序
            }
        });
        System.out.println(Arrays.toString(students));
    }
}
