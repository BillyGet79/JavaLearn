package com.itheima.service.impl;

import com.itheima.domain.User;
import com.itheima.dao.UserDao;
import com.itheima.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoyifei
 * @since 2024-07-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
