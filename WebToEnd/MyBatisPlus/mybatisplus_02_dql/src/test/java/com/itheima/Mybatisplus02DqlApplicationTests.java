package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.domain.query.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
class Mybatisplus02DqlApplicationTests {
	@Autowired
	private UserDao userDao;

	@Test
	void testGetAll() {
		//方式一：按条件查询
//		QueryWrapper<User> qw = new QueryWrapper<>();
//		qw.lt("age", 18);
//		List<User> userList = userDao.selectList(qw);
//		System.out.println(userList);

		//方式二：Lambda按条件查询
//		QueryWrapper<User> qw = new QueryWrapper<>();
//		qw.lambda().lt(User::getAge, 10);
//		List<User> userList = userDao.selectList(qw);
//		System.out.println(userList);

		//方式三：Lambda按条件查询
//		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//		lqw.lt(User::getAge, 10);
//		List<User> userList = userDao.selectList(lqw);
//		System.out.println(userList);

		//模拟页面传递过来的查询数据
		UserQuery uq = new UserQuery();
		uq.setAge(10);
		uq.setAge2(30);

		//null判定
//		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//		//10到30岁之间
//		lqw.lt(User::getAge, uq.getAge2());
//		lqw.gt(User::getAge, uq.getAge());
//		List<User> userList = userDao.selectList(lqw);
//		System.out.println(userList);

//		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//		//先判定第一个参数是否为true，如果为true连接当前条件
//		lqw.lt(uq.getAge2() != null, User::getAge, uq.getAge2());
//		lqw.gt(uq.getAge() != null, User::getAge, uq.getAge());
//		List<User> userList = userDao.selectList(lqw);
//		System.out.println(userList);

		//查询投影
//		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//		lqw.select(User::getId, User::getName, User::getAge);
//		QueryWrapper<User> lqw = new QueryWrapper<>();
//		lqw.select("id", "name", "age", "tel");
//		List<User> userList = userDao.selectList(lqw);
//		System.out.println(userList);

//		QueryWrapper<User> lqw = new QueryWrapper<>();
//		lqw.select("count(*) as count");
//		lqw.groupBy("tel");
//		List<Map<String, Object>> userList = userDao.selectMaps(lqw);
//		System.out.println(userList);

		//条件查询
//		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//		lqw.eq(User::getName, "Jerry").eq(User::getPassword, "jerry");
//		User loginUser = userDao.selectOne(lqw);
//		System.out.println(loginUser);

//		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//		//范围查询 lt le gt ge eq between
//		lqw.between(User::getAge, 10, 30);
//		List<User> userList = userDao.selectList(lqw);
//		System.out.println(userList);

//		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//		//模糊匹配 like
//		lqw.like(User::getName, "J");
//		List<User> userList = userDao.selectList(lqw);
//		System.out.println(userList);

		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
		List<User> userList = userDao.selectList(lqw);
		System.out.println(userList);

	}

}
