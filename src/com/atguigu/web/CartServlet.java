package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LiuJun
 * @create 2021-06-20-19:27
 * @description
 */
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数，商品编号
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);
        //调用bookService.queryBookById()方法得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息，变为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //最后一个添加的商品名称
        request.getSession().setAttribute("lastName", cartItem.getName());
        //重定向回原商品列表页面
        response.sendRedirect(request.getHeader("Referer"));


    }


    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数，商品编号
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);
        //调用bookService.queryBookById()方法得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息，变为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //最后一个添加的商品名称
        request.getSession().setAttribute("lastName", cartItem.getName());
        //返回购物车总的商品数量以及最后一个添加的商品名称
        Map<String, Object> resultMap = new ConcurrentHashMap<>(16);
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastItemName", cartItem.getName());
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(resultMap));

    }

    /**
     * 删除购物车商品项
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            //删除购物车商品项
            cart.deleteItem(id);
            //重定向回原页面
            response.sendRedirect(request.getHeader("Referer"));
        }

    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            //重定向回原页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
            //重定向回原页面

            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
