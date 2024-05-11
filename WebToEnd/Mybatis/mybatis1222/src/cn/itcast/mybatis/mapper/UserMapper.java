package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.po.User;

import java.util.List;

/**
 * mapper接口，用户管理，相当于dao接口
 */
public interface UserMapper {

    //根据id查询用户信息
    public User findUserById(int id) throws Exception;

    //根据用户名列查询用户列表
    //
    public List<User> findUserByName(String name) throws Exception;

    //添加用户信息
    public void insertUser(User user) throws  Exception;

    //删除用户信息
    public void deleteUser(int id) throws Exception;


}


