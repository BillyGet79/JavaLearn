package com.itheima.d1_junit;

/**
 * 业务方法
 */
public class UserService {
    public String loginName(String loginName, String passWord) {
        if ("admin".equals(loginName) && "123456".equals(passWord)) {
            return "登陆成功";
        } else {
            return "用户名或者密码有问题";
        }
    }


    public void selectNames(){
        System.out.println(5);
        System.out.println("查新全部用户名称成功~~");
    }
}
