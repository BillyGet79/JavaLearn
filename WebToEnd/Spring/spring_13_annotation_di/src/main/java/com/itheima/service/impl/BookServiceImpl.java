package com.itheima.service.impl;

import com.itheima.dao.BookDao;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    //按类型进行注入，不写setter方法也可以，这里是暴力注入
    @Autowired
    //当然，也可以指定字段类型进行注入
    @Qualifier("bookDao")
    private BookDao bookDao;

    @Override
    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
}
