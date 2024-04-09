package com.itheima.d9_proxy;

public class UserServiceImpl implements UserService{

    @Override
    public String login(String loginName, String passWord) {
        try {
            Thread.sleep(1000);
            if ("admin".equals(loginName) && "123456".equals(passWord)) {
                return "success";
            }
            return "登录名和密码可能有毛病";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public void selectUsers() {
        System.out.println("查询了100个用户数据");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteUsers() {
        try {
            System.out.println("删除了100个用户数据");
            Thread.sleep(500);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateUsers() {
        try {
            System.out.println("修改100个用户数据");
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
