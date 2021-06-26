package com.atguigu.service;

import com.atguigu.pojo.Cart;

/**
 * @author LiuJun
 * @create 2021-06-21-16:17
 * @description
 */
public interface OrderService {

    public String createOrder(Cart cart, Integer userId);



}
