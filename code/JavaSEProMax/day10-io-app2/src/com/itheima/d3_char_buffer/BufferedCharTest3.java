package com.itheima.d3_char_buffer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * 目标：完成出师表顺序的恢复，并存入到另一个新文件中去
 */
public class BufferedCharTest3 {
    public static void main(String[] args) {
        try (
                //创建缓冲字符输入流管道与源文件接通
                BufferedReader br = new BufferedReader(new FileReader("day10-io-app2\\src\\resources\\csb.txt"));
                //创建缓冲字符输出管道与目标文件接通
                BufferedWriter bw = new BufferedWriter(new FileWriter("day10-io-app2\\src\\resources\\new.txt"));
                ) {
            //定义一个List集合存储每行数据
            List<String> data = new ArrayList<>();
            //定义循环，按照行读取文章
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
            //排序
            //自定义排序规则
            List<String> sizes = new ArrayList<>();
            Collections.addAll(sizes, "一","二","三","四","五","陆","柒","八","九","十","十一");
            Collections.sort(data, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {

                    return sizes.indexOf(o1.substring(0, o1.indexOf("."))) - sizes.indexOf(o2.substring(0, o2.indexOf(".")));
                }
            });
            //写入到文件中去
            for (String datum : data) {
                bw.write(datum);
                bw.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
