package com.itheima;

import java.util.Random;

/**
 * 需求：
 *      定义方法实现随意产生一个5位的验证码，每位可能是数字、大写字母、小写字母
 */
public class Test3 {
    public static void main(String[] args) {
        //5、调用获取验证码的方法得到一个随机的验证码
        String code = creadeCode(5);
        System.out.println("随机验证码：" + code);
    }

    /**
     * 1、定义一个方法返回一个随机验证码，返回值类型：String
     */
    public static String creadeCode(int n){
        //2、定义一个for循环，循环n次，依次生成随机字符
        Random r = new Random();
        //4、定义一个字符串变量记录生成的随机字符
        String code = "";
        for (int i = 0; i < n; i++) {
            //3、生成一个随机字符：英文大写 小写 数字(0 1 2)
            int type = r.nextInt(3);
            switch (type){
                case 0:
                    //大写字符
                    char ch = (char) (r.nextInt(26) + 'A');
                    code += ch;
                    break;
                case 1:
                    char ch1 = (char) (r.nextInt(26) + 'a');
                    code += ch1;
                    //小写字符
                    break;
                case 2:
                    code += r.nextInt(10);
                    //数字
                    break;
            }
        }
        return code;
    }
}
