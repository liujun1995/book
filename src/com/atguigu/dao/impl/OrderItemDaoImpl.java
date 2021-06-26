package com.atguigu.dao.impl;

import com.atguigu.pojo.OrderItem;

/**
 * @author LiuJun
 * @create 2021-06-21-15:05
 * @description
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements com.atguigu.dao.OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

}
