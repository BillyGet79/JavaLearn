package com.itheima.d2_static_util;

import java.util.Random;

/**
 * 工具类
 */
public class ItheimaUtil {

    /**
     * 注意：由于工具类无需创建对象，所以把其构造器私有化会显得很专业！
     */
    private ItheimaUtil(){
    }
    /**
     * 静态方法
     */
    public static String createVerifyCode(int n){
        //开发一个验证码
        String code = "";
        String data = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int index = r.nextInt(data.length());
            code += data.charAt(index);
        }
        return code;
    }

}
