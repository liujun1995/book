package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiuJun
 * @create 2021-06-21-17:24
 * @description
 */
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //获取userId
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser != null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        int id = loginUser.getId();


        //调用orderService.createOrder(Cart,userid);生成订单
        String orderId = null;
        try {
            orderId = orderService.createOrder(cart, id);
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
        }

        if (orderId != null) {
            request.setAttribute("orderId", orderId);
            //请求转发到checkout.jsp
            request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
