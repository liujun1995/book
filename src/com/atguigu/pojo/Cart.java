package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.*;

/**
 * 购物车对象
 *
 * @author LiuJun
 * @create 2021-06-20-10:14
 * @description
 */
public class Cart {

    private Integer totalCount;
    private BigDecimal totalPrice = new BigDecimal(0);
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品项
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        //先查看购物车是否已添加过该商品，如果已添加，则商品的数量加一，总金额更新
        CartItem oldItem = items.get(cartItem.getId());
        if (oldItem != null) {
            oldItem.setCount(oldItem.getCount() + 1);
            oldItem.setTotalPrice(oldItem.getPrice().multiply(new BigDecimal(oldItem.getCount())));
        } else {
            //如果没有添加过则直接放入集合中
            items.put(cartItem.getId(), cartItem);
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改商品数量
     *
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(count);//修改商品的数量
            //更新该商品的总金额
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Integer getTotalCount() {

        Collection<CartItem> items = this.items.values();
        for (CartItem item : items) {
            totalCount += item.getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {

        Collection<CartItem> items = this.items.values();
        for (CartItem item : items) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }
}
