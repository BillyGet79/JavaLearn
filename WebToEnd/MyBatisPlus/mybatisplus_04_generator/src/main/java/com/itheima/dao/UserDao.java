package com.itheima.dao;

import com.itheima.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gaoyifei
 * @since 2024-07-12
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}
