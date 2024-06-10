package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.po.User;
import cn.itcast.mybatis.po.UserCustom;
import cn.itcast.mybatis.po.UserQueryVo;

import java.util.List;

/**
 * mapper接口，用户管理，相当于dao接口
 */
public interface UserMapper {

    //根据id查询用户信息
    public User findUserById(int id) throws Exception;

    //根据用户名列查询用户列表
    public List<User> findUserByName(String name) throws Exception;

    //添加用户信息
    public void insertUser(User user) throws  Exception;

    //删除用户信息
    public void deleteUser(int id) throws Exception;

    //根据用户信息查用户
    public List<User> findUserByUser(User user) throws Exception;

    //用户信息综合查询
    public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;

    //用户信息个数综合查询
    public int findUserCount(UserQueryVo userQueryVo) throws Exception;

    //resultMap映射方法测试
    public User findUserByIdResultMap(int id) throws Exception;

    //更新用户
    public void updateUser(User user) throws Exception;
}


