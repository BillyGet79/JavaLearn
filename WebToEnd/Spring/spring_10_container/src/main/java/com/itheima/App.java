package com.itheima;

import com.itheima.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //加载类路径下的配置文件
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //文件路径加载配置文件
//        ApplicationContext ctx = new FileSystemXmlApplicationContext("spring_10_container/src/main/resources/applicationContext.xml");

//        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
//        BookDao bookDao = ctx.getBean("bookDao", BookDao.class);
        //这种用法的配置文件下只能有一个这个类型的bean
        BookDao bookDao = ctx.getBean(BookDao.class);
        bookDao.save();
    }
}
