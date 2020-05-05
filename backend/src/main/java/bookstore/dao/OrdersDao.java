package com.reins.bookstore.dao;


import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.entity.Orders;

import java.util.List;

public interface OrdersDao {
    Orders findOne(Integer u_id, Integer b_id);
    Orders addOne(Integer u_id, Integer b_id, Integer num);
    List<Orders> getBelongs(Integer u_id);
}
