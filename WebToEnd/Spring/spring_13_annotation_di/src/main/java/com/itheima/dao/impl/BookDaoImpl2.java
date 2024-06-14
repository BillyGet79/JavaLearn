package com.itheima.dao.impl;

import com.itheima.dao.BookDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Repository("bookDao2")
@Scope("singleton")
public class BookDaoImpl2 implements BookDao {
    @Override
    public void save() {
        System.out.println("book dao save ...2");
    }
    @PostConstruct
    public void init() {
        System.out.println("init ...");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("destroy ...");
    }
}
