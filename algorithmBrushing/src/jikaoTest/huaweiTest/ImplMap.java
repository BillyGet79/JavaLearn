package jikaoTest.huaweiTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImplMap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);

        in.nextToken();
        int deviceAbility = (int) in.nval;
        in.nextToken();
        int num = (int) in.nval;
        int[] implAbility = new int[num];
        for (int i = 0; i < num; i++) {
            in.nextToken();
            implAbility[i] = (int) in.nval;
        }

        tryMap(deviceAbility, num, implAbility);
    }

    public static void tryMap(int deviceAbility, int num ,int[] implAbility) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        boolean ans = process(deviceAbility, deviceAbility, 0, implAbility, list1, list2);
        if (ans) {
            for (Integer value : list1) {
                System.out.print(value + " ");
            }
            System.out.println();
            for (Integer integer : list2) {
                System.out.print(integer + " ");
            }
        } else {
            System.out.println(-1);
        }
    }

    public static boolean process(int first, int second, int index, int[] implAbility, List<Integer> list1, List<Integer> list2) {
        if (index == implAbility.length) {
            if (first == 0 && second == 0) {
                return true;
            }
            return false;
        }
        //让该接口板安装在第一个设备上
        if (first - implAbility[index] >= 0) {
            list1.add(implAbility[index]);
            boolean ans = process(first - implAbility[index], second, index + 1, implAbility, list1, list2);
            if (ans) {
                return true;
            }
            //恢复环境
            list1.removeLast();
        }
        //让该接口板安装在第二个设备上
        if (second - implAbility[index] >= 0) {
            list2.add(implAbility[index]);
            boolean ans = process(first, second - implAbility[index], index + 1, implAbility, list1, list2);
            if (ans) {
                return true;
            }
            //恢复环境
            list2.removeLast();
        }
        //不安装在任意一个设备上
        return process(first, second, index + 1, implAbility, list1, list2);
    }

}
