package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author LiuJun
 * @create 2021-06-16-7:28
 * @description
 */
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
        pageNo+=1;
        //获取请求的参数，封装成book对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //调用bookService.addBook方法保存图书、
        bookService.addBook(book);

        //请求重定向到/manager/bookServlet?action=bookList，防止请求转发时，用户按F5连续发起添加图书的请求
        //response.sendRedirect方法的路径从端口号开始
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数id
        String id = request.getParameter("id");
        //根据id删除图书
        bookService.deleteBookById(Integer.parseInt(id));
        //重定向回图书列表管理
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //调用BookService.update(book) 修改图书
        bookService.updateBook(book);
        //重定向回图书列表管理页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void bookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //把全部图书保存到request域中
        request.setAttribute("books", books);
        //请求转发到/pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数图书编号
        int id = Integer.parseInt(request.getParameter("id"));
        //调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        //保存图书到request域中
        request.setAttribute("book", book);
        //请求转发到pages/manager/book_edit.jsp页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);

    }

    /**
     * 处理分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数，pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用bookService.page(pageNo,pageSize):返回page对象
        Page<Book> page = bookService.page(pageNo,pageSize);

        page.setUrl("manager/bookServlet?action=page");

        //保存page对象到request域中
        request.setAttribute("page", page);
        //请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

}
