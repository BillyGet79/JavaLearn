package cn.itcast.mybatis.dao;

import cn.itcast.mybatis.po.User;

import java.util.List;

/**
 * dao接口，用户管理
 */
public interface UserDao {

    //根据id查询用户信息
    public User findeUserById(int id) throws Exception;

    //根据用户名列查询用户列表
    public List<User> findUserByName(String name) throws Exception;

    //添加用户信息
    public void insertUser(User user) throws Exception;

    //删除用户信息
    public void deleteUser(User user) throws Exception;



}


