package cn.itcast.mybatis.dao;

import cn.itcast.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class UserDaoImplTest {

    private SqlSessionFactory sqlSessionFactory;

    //此方法是在执行testFindUserById之前执行
    @Before
    public void setUp() throws Exception {
        //创建sqlSessionFactory
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindUserById() throws Exception {
        //创建UserDao的对象
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);

        //调用UserDao的方法
        User user = userDao.findeUserById(1);

        System.out.println(user);
    }

}
