package com.itheima.service;

import com.itheima.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BookService {
    /**
     * 保存
     * @param book
     * @return
     */
    public boolean save(Book book);

    /**
     * 修改
     * @param book
     * @return
     */
    public boolean update(Book book);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 通过id查找图书
     * @param id
     * @return
     */
    public Book getById(Integer id);

    /**
     * 查找所有图书
     * @return
     */
    public List<Book> getAll();
}
