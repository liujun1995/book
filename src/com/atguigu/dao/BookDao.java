package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @author LiuJun
 * @create 2021-06-15-20:04
 * @description
 */
public interface BookDao {

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, Integer pageSize, int min, int max);
}
