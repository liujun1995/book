package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author LiuJun
 * @create 2021-06-15-22:29
 * @description
 */
public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        //imgPath使用默认值"static/img/default.jpg"
        bookDao.addBook(new Book(null, "十万个为什么", "宋红康",
                new BigDecimal("23.00"), 9999, 0, null));
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {

        bookDao.updateBook(new Book(21, "十万个为什么", "宋红康",
                new BigDecimal(23.15), 9999, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        bookDao.queryBooks().forEach(System.out::println);
    }
}