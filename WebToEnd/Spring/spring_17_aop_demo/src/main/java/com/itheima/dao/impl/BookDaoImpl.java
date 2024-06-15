package com.itheima.dao.impl;

import com.itheima.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println("book dao save");
        }
        Long endTime = System.currentTimeMillis();
        Long totalTime = endTime - startTime;
        System.out.println("执行万次消耗时间：" + totalTime + "ms");
    }

    @Override
    public void update() {
        System.out.println("book dao update ...");
    }

    @Override
    public void delete() {
        System.out.println("book dao delete ...");
    }

    @Override
    public void select() {
        System.out.println("book dao select ...");
    }
}
