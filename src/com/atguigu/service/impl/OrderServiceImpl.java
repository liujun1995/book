package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author LiuJun
 * @create 2021-06-21-16:19
 * @description
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    //当生成订单后，为了使库存减少，销量增加，需要bookdao
    private BookDao bookDao = new BookDaoImpl();


    @Override
    public String createOrder(Cart cart, Integer userId) {

        //订单号的唯一性
        String orderId = System.currentTimeMillis() + "" + userId;
        //有外键约束，首先应保存订单，再保存订单项
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        if (cart.getTotalPrice()!=new BigDecimal(0)){
            orderDao.saveOrder(order);
            //遍历购物车中每一个商品项，转化成为订单项保存到数据库
            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                CartItem cartItem = entry.getValue();
                OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), order.getOrderId());
                orderItemDao.saveOrderItem(orderItem);
                //更新图书的库存和销量
                Book book = bookDao.queryBookById(cartItem.getId());
                book.setSales(book.getSales()+cartItem.getCount());
                book.setStock(book.getStock()-cartItem.getCount());
                bookDao.updateBook(book);

            }
            //生成订单则需清空购物车中的商品
            cart.clear();
            return orderId;
        }
        return null;
    }
}
