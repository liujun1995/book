package com.atguigu.service;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

/**
 * @author LiuJun
 * @create 2021-06-15-22:58
 * @description
 */
public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Page<Book> page(int pageNo,int pageSize);

    Page<Book> pageByPrice(int pageNo, Integer pageSize, int min, int max);
}
