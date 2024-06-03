package cn.itcast.mybatis.dao;

import cn.itcast.mybatis.mapper.OrdersMapperCustom;
import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class OrdersMapperCustomTest {

    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void setUp() throws Exception {
        //创建sqlSessionFactory
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        List<OrdersCustom> list = ordersMapperCustom.findOrdersUser();
        sqlSession.close();
        System.out.println(list);
    }

    @Test
    public void testFindOrdersUserResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        List<Orders> list = ordersMapperCustom.findOrdersUserResultMap();
        sqlSession.close();
        System.out.println(list);
    }
}
