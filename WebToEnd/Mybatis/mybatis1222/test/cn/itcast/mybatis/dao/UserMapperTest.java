package cn.itcast.mybatis.dao;

import cn.itcast.mybatis.mapper.UserMapper;
import cn.itcast.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserMapperTest {

    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void setUp() throws Exception {
        //创建sqlSessionFactory
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindUserById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用userMapper的方法
        User user = userMapper.findUserById(1);
        sqlSession.close();
        System.out.println(user);
    }

    @Test
    public void testFindUserByName() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.findUserByName("王五");
        sqlSession.close();
        System.out.println(list);
    }

    @Test
    public void testDeleteUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUser(27);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User("王小军", "1", new Date(), "河南郑州");
        userMapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
    }




}
