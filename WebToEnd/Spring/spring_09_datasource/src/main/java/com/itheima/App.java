package com.itheima;

import com.itheima.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
//        System.out.println(dataSource);

        BookDao bookDao = (BookDao) applicationContext.getBean("bookDao");
        bookDao.save();

    }
}
