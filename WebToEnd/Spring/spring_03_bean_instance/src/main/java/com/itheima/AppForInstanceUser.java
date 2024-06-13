package com.itheima;

import com.itheima.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForInstanceUser {
    public static void main(String[] args) {
//        //创建实例工厂对象
//        UserDaoFactory userDaoFactory = new UserDaoFactory();
//        //通过实例工厂对象创建对象
//        UserDao userDao = userDaoFactory.getUserDao();
//        userDao.save();


        //在执行这一段语句之后，applicationContext.xml中定义的所有的bean都会实例化，所以所有的bean都会调用构造函数
        //这就是为什么在xml中没有注释掉的其他bean会调用构造函数，导致有额外输出的原因
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDao userDao1 = (UserDao) ctx.getBean("userDao");
        UserDao userDao2 = (UserDao) ctx.getBean("userDao");
        System.out.println(userDao1);
        System.out.println(userDao2);
//        userDao.save();

    }
}
