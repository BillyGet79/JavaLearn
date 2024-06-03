package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.po.Orderdetail;
import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;

import java.util.List;

/**
 * 订单mapper
 */
public interface OrdersMapperCustom {

    //查询订单关联查询用户信息
    public List<OrdersCustom> findOrdersUser() throws Exception;

    //使用resultMap查询订单关联查询用户信息
    public List<Orders> findOrdersUserResultMap() throws Exception;
}
