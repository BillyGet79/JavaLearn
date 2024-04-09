package com.itheima.d2_static_util;

import java.util.ArrayList;

public class ArrayUtils {

    private ArrayUtils(){
    }

    public static String toString(ArrayList<Integer> arr){
        String s = "[";
        for (int i = 0; i < arr.size(); i++) {
            s += i != arr.size() - 1? arr.get(i) + ", " : arr.get(i);
        }
        s += "]";
        return s;
    }

    public static double getAverage(ArrayList<Double> arr){
        Double sum = 0.0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        return sum / arr.size();
    }

}
