package com.itheima.d3_charset;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 目标：学会自己进行文字的编码和解码，为以后可能用到的场景做准备
 */
public class Test {
    public static void main(String[] args) {
        //1、编码，把文字转换成字节（使用指定的编码）
        String name = "abc我爱你中国";
        byte[] bytes = new byte[0]; //以当前代码默认字符集进行编码（UTF-8）
        try {
            bytes = name.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes));

        //2、解码：把字节转换成对应的中文形式（编码前 和 编码后的字符集必须一致，否则乱码）
        String rs = null;
        try {
            rs = new String(bytes, "GBK");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(rs);
    }
}
