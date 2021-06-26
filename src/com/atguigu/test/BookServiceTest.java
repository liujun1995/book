package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author LiuJun
 * @create 2021-06-16-10:49
 * @description
 */
public class BookServiceTest {

    BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(22);
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
    }
}