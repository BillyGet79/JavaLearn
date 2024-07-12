package com.itheima;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Mybatisplus03DmlApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testSave() {
        User user = new User();
//        user.setId(667L);
        user.setName("黑马程序员");
        user.setPassword("itheima");
        user.setAge(12);
        user.setTel("4006184000");
        userDao.insert(user);
    }

    @Test
    void testDelete() {
//        List<Long> list = new ArrayList<>();
//        list.add(1811586700507226114L);
//        list.add(1811588654167900161L);
//        list.add(1811588889099317250L);
//        userDao.deleteBatchIds(list);


//        List<Long> list = new ArrayList<>();
//        list.add(1L);
//        list.add(3L);
//        list.add(4L);
//        userDao.selectBatchIds(list);

        userDao.deleteById(2L);
//        System.out.println(userDao.selectList(null));

    }

    @Test
    void testUpdate() {
//        User user = new User();
//        user.setId(3L);
//        user.setName("Jock666");
//        user.setVersion(1);
//        userDao.updateById(user);
//
//        //1、先通过要修改的数据id将当前数据查询出来
//        User user = userDao.selectById(3L);
//        //2、将要修改的属性逐一设置进去
//        user.setName("Jock888");
//        userDao.updateById(user);

        //1、先通过要修改的数据id将当前数据查询出来
        User user = userDao.selectById(3L);     //version = 3

        User user2 = userDao.selectById(3L);    //version = 4
        user2.setName("Jock888");
        userDao.updateById(user2);      //version = 4

        //2、将要修改的属性逐一设置进去
        user.setName("Jock888");
        userDao.updateById(user);       //version != 3, 所以这一条的修改失效


    }

}
