package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.po.Orderdetail;
import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;
import cn.itcast.mybatis.po.User;

import java.util.List;

/**
 * 订单mapper
 */
public interface OrdersMapperCustom {

    //查询订单关联查询用户信息
    public List<OrdersCustom> findOrdersUser() throws Exception;

    //使用resultMap查询订单关联查询用户信息
    public List<Orders> findOrdersUserResultMap() throws Exception;

    //订单及订单明细
    public List<Orders> findOrdersAndOrderDetailsResultMap() throws Exception;

    //用户及用户商品查询
    public List<User> findUsersAndUserItemsResultMap() throws Exception;

    //查询订单关联查询用户，用户信息是延迟加载
    public List<Orders> findOrdersUserLazyLoading() throws Exception;
}
